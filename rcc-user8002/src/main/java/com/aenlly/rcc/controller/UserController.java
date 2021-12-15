package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.User;
import com.aenlly.rcc.service.IUserService;
import com.aenlly.rcc.utils.CodeResult;
import com.aenlly.rcc.utils.CommonResult;
import com.aenlly.rcc.utils.MessageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户前端控制器
 *
 * @author aenlly
 * @since 2021-12-11
 */
@RestController
@Log4j2
@Api(tags = "用户管理")
@RequestMapping("/user")
public class UserController {

  @Resource private IUserService userService;

  @ApiOperation(value = "用户信息请求,积分排行", httpMethod = "GET")
  @GetMapping(value = "/getByPoint")
  public CommonResult<List<User>> getUserListByPoint() {
    List<User> list = userService.getUserListByPoint();
    return resultOk(list);
  }

  /**
   * 操作成功统一返回列表内容构造操作执行方法
   *
   * @param users 列表内容
   * @return 返回内容
   */
  private CommonResult<List<User>> resultOk(List<User> users) {
    return new CommonResult<>(CodeResult.OK, MessageResult.OK, users);
  }

  /**
   * 操作成功统一返回单个内容构造操作
   *
   * @param user 单一实体内容
   * @return 返回内容
   */
  private CommonResult<User> resultOk(User user) {
    return new CommonResult<>(CodeResult.OK, MessageResult.OK, user);
  }

  /**
   * 操作失败执行方法
   *
   * @return 返回内容
   */
  private CommonResult<List<User>> resultErrorList() {
    return new CommonResult<>(CodeResult.ERROR, MessageResult.ERROR, null);
  }

  /**
   * 操作失败执行方法
   *
   * @return 返回内容
   */
  private CommonResult<User> resultErrorOne() {
    return new CommonResult<>(CodeResult.ERROR, MessageResult.ERROR, null);
  }
}
