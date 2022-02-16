package com.aenlly.rcc.admin.controller;

import com.aenlly.rcc.admin.service.IAdminTableService;
import com.aenlly.rcc.utils.CommonResult;
import com.aenlly.rcc.vo.LoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.aenlly.rcc.utils.ResultUtil.resultError;
import static com.aenlly.rcc.utils.ResultUtil.resultOk;

/**
 * @author Aenlly
 * @create by date 2022/01/08 17:37
 * @projectName RefuseClassificationCultivate
 */
@RestController
@Api(tags = "管理员登录信息管理控制器")
public class LoginController {

  @Resource private IAdminTableService adminTableService;

  @ApiOperation(value = "登录请求", httpMethod = "POST")
  @PostMapping("/login")
  public CommonResult<LoginVo> login(
      @Param("账号") @RequestParam("username") String username,
      @Param("密码") @RequestParam("password") String password) {
    try {
      LoginVo token = adminTableService.adminLogin(username, password);
      return resultOk(token);
    } catch (Exception e) {
      return resultError();
    }
  }
}
