package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.PointsLog;
import com.aenlly.rcc.service.IPointsLogService;
import com.aenlly.rcc.utils.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static com.aenlly.rcc.utils.ResultUtil.resultError;
import static com.aenlly.rcc.utils.ResultUtil.resultOk;

/**
 * 前端控制器
 *
 * @author aenlly
 * @since 2021-12-16
 */
@RestController
@Api(tags = "积分记录管理控制器")
@RequestMapping("/points-log")
public class PointsLogController {

  @Resource IPointsLogService pointsLogService;

  @ApiOperation(value = "积分记录请求", httpMethod = "GET")
  @GetMapping("/getByUserIdAndType")
  public CommonResult<List<PointsLog>> getPointsLogByUserIdList(
      @Param("用户编号") @RequestParam("userId") String userId,
      @Param("积分记录类型") @RequestParam("type") Integer type) {
    try {
      List<PointsLog> list = pointsLogService.getPointsLogByUserIdList(userId, type);
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }
}
