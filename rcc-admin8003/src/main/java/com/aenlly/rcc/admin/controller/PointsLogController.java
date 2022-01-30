package com.aenlly.rcc.admin.controller;

import com.aenlly.rcc.admin.service.IPointsLogService;
import com.aenlly.rcc.entity.PointsLog;
import com.aenlly.rcc.utils.CommonResult;
import com.aenlly.rcc.utils.enums.QueryPointsLogTypeEnum;
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
 * 用户积分记录 前端控制器
 *
 * @author aenlly
 * @since 2022-01-30
 */
@RestController
@Api(tags = "积分管理-用户积分记录前端控制器")
@RequestMapping("/points-log")
public class PointsLogController {

  @Resource private IPointsLogService service;

  @ApiOperation(value = "请求积分记录信息数据", httpMethod = "GET")
  @GetMapping("/getList")
  public CommonResult<IPage<PointsLog>> getList(
      @Param("当前页码") @RequestParam("current") Integer current,
      @Param("每页数量") @RequestParam("size") Integer size,
      @Param("查询类型") @RequestParam("queryType") QueryPointsLogTypeEnum queryType,
      @Param("查询内容") @RequestParam("text") String text) {
    try {
      IPage<PointsLog> list = service.getList(new Page<>(current, size), queryType, text);
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }
}
