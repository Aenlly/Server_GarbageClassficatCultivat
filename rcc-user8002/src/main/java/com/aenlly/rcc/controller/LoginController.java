package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.User;
import com.aenlly.rcc.service.ILoginService;
import com.aenlly.rcc.utils.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
@Slf4j
public class LoginController {

  @Resource private ILoginService loginService;

  @ApiOperation(value = "用户登录请求", httpMethod = "GET")
  @GetMapping("/login")
  public CommonResult<User> userLogin(
      @Param(value = "用户临时登录凭证") String code,
      @Param(value = "用户昵称") String nickName,
      @Param(value = "头像地址") String avatarUrl) {
    try {
      User user = loginService.userLogin(code, nickName, avatarUrl);
      return resultOk(user);
    } catch (Exception e) {
      return resultError();
    }
  }
}
