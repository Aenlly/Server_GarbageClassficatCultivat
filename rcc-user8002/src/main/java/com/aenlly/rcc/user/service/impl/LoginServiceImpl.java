package com.aenlly.rcc.user.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.aenlly.rcc.entity.LoginUser;
import com.aenlly.rcc.entity.Points;
import com.aenlly.rcc.entity.User;
import com.aenlly.rcc.user.service.ILoginService;
import com.aenlly.rcc.user.service.IPointsService;
import com.aenlly.rcc.user.service.IUserService;
import com.aenlly.rcc.utils.JWTUtil;
import com.aenlly.rcc.utils.WxParam;
import com.aenlly.rcc.vo.LoginUserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author Aenlly
 * @create by date 2022/01/08 20:02
 * @projectName RefuseClassificationCultivate
 */
@Service
@Slf4j
public class LoginServiceImpl implements ILoginService {
  /** 用户表服务对象 */
  @Resource private IUserService userService;
  /** 积分服务对象 */
  @Resource private IPointsService pointsService;
  /** http请求服务对象 */
  @Resource private RestTemplate restTemplate;

  @Resource AuthenticationManager authenticationManager;

  /**
   * 用户登录，不存在则创建
   *
   * @param code 用户临时登录凭证
   * @param nickName 用户昵称
   * @param avatarUrl 头像地址
   * @return 用户信息
   */
  @Override
  public LoginUserVo userLogin(String code, String nickName, String avatarUrl) {
    // 使用临时登录凭证换取微信唯一标识openid
    String user_id = getOpenIdByCode(code);
    // 查询库中是否有该用户
    User user = userService.getById(user_id);

    // 创建用户或者更新用户信息
    boolean b = create(user_id, nickName, avatarUrl);
    // 判断是否创建成功该用户
    if (b) {
      // 查询用户信息
      user = userService.getUserById(user_id);
    } else {
      throw new NullPointerException(); // 异常
    }

    // 获得积分头衔
    Points points = pointsService.getById(user.getPointsId());
    // 设置积分头衔对象

    user.setPoints(points);

    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(user_id, user_id);
    Authentication authenticate = authenticationManager.authenticate(authenticationToken);
    // 认证未通过
    if (ObjectUtil.isNull(authenticate)) {
      throw new NullPointerException();
    }

    LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
    String json = JSONUtil.toJsonPrettyStr(loginUser);
    String token = JWTUtil.createToken(json);

    return new LoginUserVo(
        user.getNickName(),
        user.getAvatarUrl(),
        user.getAccumulativePoints(),
        user.getRemainingPoints(),
        user.getAnswerPoints(),
        user.getPointsId(),
        points,
        token);
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
    log.error(user.toString());
    return userService.saveOrUpdate(user);
  }
}
