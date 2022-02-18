package com.aenlly.rcc.user.controller;

import com.aenlly.rcc.entity.User;
import com.aenlly.rcc.user.service.IUserService;
import com.aenlly.rcc.user.utils.TokenUtil;
import com.aenlly.rcc.utils.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static com.aenlly.rcc.utils.ResultUtil.resultError;
import static com.aenlly.rcc.utils.ResultUtil.resultOk;

/**
 * 用户表 前端控制器
 *
 * @author aenlly
 * @since 2021-12-11
 */
@RestController
@Api(tags = "用户信息管理控制器")
@RequestMapping("/user")
public class UserController {

  @Resource private IUserService userService;

  @ApiOperation(value = "用户积分信息列表请求,积分排行", httpMethod = "GET")
  @GetMapping("/getByPoint")
  public CommonResult<List<User>> getUserListByPoint() {
    try {
      List<User> list = userService.getUserListByPoint();
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "服务-知识测验-排行", httpMethod = "GET")
  @GetMapping("/getByAnswerPoints")
  public CommonResult<List<User>> getUserListByAnswerPoints() {
    try {
      List<User> list = userService.getUserListByAnswerPoints();
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "用户信息请求", httpMethod = "GET")
  @GetMapping("/getById")
  public CommonResult<User> getById(@Param("token") @RequestHeader("token") String token) {
    try {
      String userId = TokenUtil.toUserId(token);
      User user = userService.getById(userId);
      return resultOk(user);
    } catch (Exception e) {
      return resultError();
    }
  }
}
