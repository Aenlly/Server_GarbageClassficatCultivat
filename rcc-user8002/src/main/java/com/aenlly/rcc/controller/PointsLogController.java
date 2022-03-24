package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.PointsLog;
import com.aenlly.rcc.service.IPointsLogService;
import com.aenlly.rcc.utils.CommonResult;
import com.aenlly.rcc.utils.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

  @Resource private IPointsLogService pointsLogService;

  @ApiOperation(value = "积分记录请求")
  @GetMapping("/getByUserIdAndType")
  public CommonResult<List<PointsLog>> getPointsLogByUserIdList(
      @ApiParam("token") @RequestHeader("token") String token,
      @ApiParam("积分记录类型") @RequestParam("type") Integer type) {
    try {
      String userId = TokenUtil.toUserId(token);
      List<PointsLog> list = pointsLogService.getPointsLogByUserIdList(userId, type);
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "每日签到请求")
  @PutMapping("/dailyCheck")
  public CommonResult<Boolean> dailyCheck(@ApiParam("token") @RequestHeader("token") String token) {
    try {
      String userId = TokenUtil.toUserId(token);
      boolean save = pointsLogService.dailyCheck(userId);
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
