package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.OrderUserView;
import com.aenlly.rcc.service.IOrderUserViewService;
import com.aenlly.rcc.utils.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

  @Resource IOrderUserViewService orderUserViewService;

  @ApiOperation(value = "用户请求订单记录", httpMethod = "GET")
  @GetMapping("/getOrderUserList/{userId}/{state}")
  public CommonResult<List<OrderUserView>> getOrderUserList(
      @Param("用户编号") @PathVariable("userId") String userId,
      @Param("订单状态") @PathVariable("state") String state) {
    try {
      List<OrderUserView> list = orderUserViewService.getOrderUserList(userId, state);
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }
}
