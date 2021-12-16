package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.PointsLog;
import com.aenlly.rcc.service.IPointsLogService;
import com.aenlly.rcc.utils.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.aenlly.rcc.utils.ResultUtil.*;

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

  @ApiOperation(value = "每日签到请求", httpMethod = "PUT")
  @PutMapping("/dailyCheck")
  public CommonResult<Boolean> dailyCheck(@Param("用户编号") @RequestParam("userId") String userId) {
    try {

      boolean save = pointsLogService.dailyCheck(userId);
      System.out.println(save);
      if (save) {
        return resultOk(true);
      } else {
        return resultExist();
      }
    } catch (Exception e) {
      return resultError();
    }
  }
}
