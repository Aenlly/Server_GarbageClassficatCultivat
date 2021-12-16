package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.PointsLog;
import com.aenlly.rcc.service.IPointsLogService;
import com.aenlly.rcc.utils.CodeResult;
import com.aenlly.rcc.utils.CommonResult;
import com.aenlly.rcc.utils.MessageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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

  @ApiOperation("根据编号与积分记录类型获得积分记录")
  @GetMapping("/getByUserIdAndType")
  public CommonResult<List<PointsLog>> getPointsLogByUserIdList(
      @Param("用户编号") @RequestParam("userId") String userId,
      @Param("积分记录类型") @RequestParam("type") Integer type) {
    try {
      List<PointsLog> list = pointsLogService.getPointsLogByUserIdList(userId, type);
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
  private CommonResult<PointsLog> resultOk(PointsLog entity) {
    return new CommonResult<>(CodeResult.OK, MessageResult.OK, entity);
  }

  /**
   * 操作成功统一返回列表内容构造操作
   *
   * @param list 列表实体内容
   * @return 返回内容
   */
  private CommonResult<List<PointsLog>> resultOk(List<PointsLog> list) {
    return new CommonResult<>(CodeResult.OK, MessageResult.OK, list);
  }

  /**
   * 操作失败执行方法
   *
   * @return 返回内容
   */
  private CommonResult<List<PointsLog>> resultErrorList() {
    return new CommonResult<>(CodeResult.ERROR, MessageResult.ERROR, null);
  }

  /**
   * 操作失败执行方法
   *
   * @return 返回内容
   */
  private CommonResult<PointsLog> resultErrorOne() {
    return new CommonResult<>(CodeResult.ERROR, MessageResult.ERROR, null);
  }
}
