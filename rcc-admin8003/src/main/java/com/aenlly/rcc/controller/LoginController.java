package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.AdminTable;
import com.aenlly.rcc.service.IAdminTableService;
import com.aenlly.rcc.utils.CommonResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin // 允许跨域请求
public class LoginController {

  @Resource private IAdminTableService adminTableService;

  @PostMapping("/login")
  public CommonResult<AdminTable> login(
      @Param("账号") @RequestParam("username") String username,
      @Param("密码") @RequestParam("password") String password) {
    try {
      AdminTable adminTable = adminTableService.adminLogin(username, password);
      return resultOk(adminTable);
    } catch (Exception e) {
      return resultError();
    }
  }
}
