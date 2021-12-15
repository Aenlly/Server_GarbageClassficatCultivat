package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.OrderUserView;
import com.aenlly.rcc.service.IOrderUserViewService;
import com.aenlly.rcc.utils.CodeResult;
import com.aenlly.rcc.utils.CommonResult;
import com.aenlly.rcc.utils.MessageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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

  @ApiOperation("用户请求订单记录")
  @GetMapping("/getOrderUserList/{userId}/{state}")
  public CommonResult<List<OrderUserView>> getOrderUserList(
      @Param("用户编号") @PathVariable("userId") String userId,
      @Param("订单状态") @PathVariable("state") String state) {
    try {
      List<OrderUserView> list = orderUserViewService.getOrderUserList(userId, state);
      return resultOk(list);
    } catch (Exception e) {
      return resultErrorList();
    }
  }

  /**
   * 操作成功统一返回单个内容构造操作
   *
   * @param entity 单一实体内容
   * @return 返回内容
   */
  private CommonResult<OrderUserView> resultOk(OrderUserView entity) {
    return new CommonResult<>(CodeResult.OK, MessageResult.OK, entity);
  }

  /**
   * 操作成功统一返回列表内容构造操作
   *
   * @param list 列表实体内容
   * @return 返回内容
   */
  private CommonResult<List<OrderUserView>> resultOk(List<OrderUserView> list) {
    return new CommonResult<>(CodeResult.OK, MessageResult.OK, list);
  }

  /**
   * 操作失败执行方法
   *
   * @return 返回内容
   */
  private CommonResult<List<OrderUserView>> resultErrorList() {
    return new CommonResult<>(CodeResult.ERROR, MessageResult.ERROR, null);
  }

  /**
   * 操作失败执行方法
   *
   * @return 返回内容
   */
  private CommonResult<OrderUserView> resultErrorOne() {
    return new CommonResult<>(CodeResult.ERROR, MessageResult.ERROR, null);
  }
}
