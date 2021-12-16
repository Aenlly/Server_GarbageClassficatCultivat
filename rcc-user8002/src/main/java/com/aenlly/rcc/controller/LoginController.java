package com.aenlly.rcc.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.aenlly.rcc.entity.Points;
import com.aenlly.rcc.entity.User;
import com.aenlly.rcc.service.IPointsService;
import com.aenlly.rcc.service.IUserService;
import com.aenlly.rcc.utils.CommonResult;
import com.aenlly.rcc.utils.WxParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

import static com.aenlly.rcc.utils.ResultUtil.resultError;
import static com.aenlly.rcc.utils.ResultUtil.resultOk;

/**
 * @author Aenlly
 * @create by date 2021/12/12 13:25
 * @projectName RefuseClassificationCultivate
 */
@RestController
@Api(tags = "登录管理")
@Log4j2
public class LoginController {

  @Resource IUserService userService;
  @Resource IPointsService pointsService;
  @Resource private RestTemplate restTemplate;

  @ApiOperation(value = "用户登录请求", httpMethod = "GET")
  @GetMapping("/login")
  public CommonResult<User> userLogin(
      @Param(value = "用户临时登录凭证") String code,
      @Param(value = "用户昵称") String nickName,
      @Param(value = "头像地址") String avatarUrl) {
    try {
      // 使用临时登录凭证换取微信唯一标识openid
      String user_id = getOpenIdByCode(code);
      // 查询库中是否有该用户
      User user = userService.getById(user_id);
      // 是否存在该用户
      if (user == null) {
        // 创建用户
        boolean b = create(user_id, nickName, avatarUrl);
        // 判断是否创建成功该用户
        if (b) {
          // 查询用户信息
          user = userService.getById(user_id);
        } else {
          return resultError();
        }
      }
      // 获得积分头衔
      Points points = pointsService.getById(user.getPointsId());
      // 设置积分头衔对象
      user.setPoints(points);
      return resultOk(user);
    } catch (Exception e) {
      return resultError();
    }
  }

  /**
   * 根据临时凭证获得OpenId
   *
   * @param code 临时凭证
   * @return OpenId
   */
  private String getOpenIdByCode(String code) {
    // url请求地址
    String url =
        String.format(
            WxParam.WX_URL.getValue(), WxParam.APPID.getValue(), WxParam.SECRET.getValue(), code);
    // 请求结果
    String wxResult = restTemplate.getForObject(url, String.class);
    // 转json格式
    JSONObject json = JSONUtil.parseObj(wxResult);
    // 判断错误码
    if (json.containsKey("errcode")) {
      Long errcode = json.get("errcode", Long.class);
      if (40029 == errcode) {
        throw new RuntimeException("微信code无效");
      } else if (45011 == errcode) {
        throw new RuntimeException("频率限制，每个用户每分钟只能请求100次");
      } else if (40226 == errcode) {
        throw new RuntimeException("高风险用户");
      } else if (-1 == errcode) {
        throw new RuntimeException("微信系统繁忙");
      }
    }
    return json.get("openid", String.class);
  }

  /**
   * 创建用户信息
   *
   * @param openId 用户标识
   * @param nickName 用户昵称
   * @param avatarUrl 用户头像地址
   * @return 是否成功创建
   */
  private boolean create(String openId, String nickName, String avatarUrl) {
    User user = new User();
    user.setUserId(openId);
    user.setNickName(nickName);
    user.setAvatarUrl(avatarUrl);
    return userService.save(user);
  }
}
