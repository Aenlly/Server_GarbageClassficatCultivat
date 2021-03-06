package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.OrderUserView;
import com.aenlly.rcc.enums.OrderStateEnum;
import com.aenlly.rcc.service.IOrderUserViewService;
import com.aenlly.rcc.utils.CommonResult;
import com.aenlly.rcc.utils.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.aenlly.rcc.utils.ResultUtil.resultError;
import static com.aenlly.rcc.utils.ResultUtil.resultOk;

/**
 * VIEW 前端控制器
 *
 * @author aenlly
 * @since 2021-12-15
 */
@RestController
@Api(tags = "用户-订单记录管理控制器")
@RequestMapping("/order-user-view")
public class OrderUserViewController {

  @Resource private IOrderUserViewService orderUserViewService;

  @ApiOperation(value = "用户请求订单记录")
  @GetMapping("/getOrderUserList/{state}")
  public CommonResult<List<OrderUserView>> getOrderUserList(
      @ApiParam("token") @RequestHeader("token") String token,
      @ApiParam("订单状态") @PathVariable("state") OrderStateEnum state) {
    try {
      String userId = TokenUtil.toUserId(token);
      List<OrderUserView> list = orderUserViewService.getOrderUserList(userId, state);
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }
}
