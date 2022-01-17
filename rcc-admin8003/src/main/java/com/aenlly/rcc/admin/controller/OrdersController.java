package com.aenlly.rcc.admin.controller;

import com.aenlly.rcc.admin.service.IOrdersService;
import com.aenlly.rcc.enums.OrderStateEnum;
import com.aenlly.rcc.utils.CommonResult;
import com.aenlly.rcc.utils.enums.QueryOrderTypeEnum;
import com.aenlly.rcc.vo.OrderVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.aenlly.rcc.utils.ResultUtil.resultError;
import static com.aenlly.rcc.utils.ResultUtil.resultOk;

/**
 * 订单记录表 前端控制器
 *
 * @author aenlly
 * @since 2022-01-16
 */
@RestController
@Api(tags = "礼品信息管理-订单管理控制器")
@RequestMapping("/orders")
public class OrdersController {

  @Resource private IOrdersService service;

  @ApiOperation(value = "请求订单信息数据", httpMethod = "GET")
  @GetMapping("/getList")
  public CommonResult<IPage<OrderVo>> getList(
      @Param("当前页码") @RequestParam("current") Integer current,
      @Param("每页数量") @RequestParam("size") Integer size,
      @Param("查询类型") @RequestParam("queryType") QueryOrderTypeEnum queryType,
      @Param("查询内容") @RequestParam("text") String text,
      @Param("核销状态(非必须)") @RequestParam(value = "state", required = false) OrderStateEnum state) {
    try {
      IPage<OrderVo> list = service.getList(new Page<>(current, size), queryType, text, state);
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "核销订单操作请求", httpMethod = "PUT")
  @PutMapping("/writeOff/{id}")
  public CommonResult<Boolean> writeOff(@Param("订单编号") @PathVariable("id") Long id) {
    try {
      Boolean b = service.writeOff(id);
      return resultOk(b);
    } catch (Exception e) {
      return resultError();
    }
  }
}
