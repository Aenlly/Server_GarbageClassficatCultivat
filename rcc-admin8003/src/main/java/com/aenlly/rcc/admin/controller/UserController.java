package com.aenlly.rcc.admin.controller;

import com.aenlly.rcc.admin.service.IUserService;
import com.aenlly.rcc.entity.OrderUserView;
import com.aenlly.rcc.entity.PointsLog;
import com.aenlly.rcc.utils.CommonResult;
import com.aenlly.rcc.vo.UserVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.aenlly.rcc.utils.ResultUtil.resultError;
import static com.aenlly.rcc.utils.ResultUtil.resultOk;

/**
 * 用户信息表 前端控制器
 *
 * @author aenlly
 * @since 2022-01-15
 */
@RestController
@Api(tags = "用户信息控制器")
@RequestMapping("/user")
public class UserController {

  @Resource private IUserService service;

  @ApiOperation(value = "请求用户信息数据", httpMethod = "GET")
  @GetMapping("/getList")
  public CommonResult<IPage<UserVo>> getList(
      @Param("当前页码") @RequestParam("current") Integer current,
      @Param("每页数量") @RequestParam("size") Integer size,
      @Param("查询内容") @RequestParam("text") String text) {
    try {
      IPage<UserVo> list = service.getList(new Page<>(current, size), text);
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "请求用户订单记录信息数据", httpMethod = "GET")
  @GetMapping("/getOrderListById")
  public CommonResult<IPage<OrderUserView>> getOrderListById(
      @Param("当前页码") @RequestParam("current") Integer current,
      @Param("每页数量") @RequestParam("size") Integer size,
      @Param("查询内容") @RequestParam("id") String userId) {
    try {
      IPage<OrderUserView> list = service.getOrderListById(new Page<>(current, size), userId);
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "请求用户积分记录信息数据", httpMethod = "GET")
  @GetMapping("/getPointsListById")
  public CommonResult<IPage<PointsLog>> getPointsListById(
      @Param("当前页码") @RequestParam("current") Integer current,
      @Param("每页数量") @RequestParam("size") Integer size,
      @Param("查询内容") @RequestParam("id") String userId) {
    try {
      IPage<PointsLog> list = service.getPointsListById(new Page<>(current, size), userId);
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }
}