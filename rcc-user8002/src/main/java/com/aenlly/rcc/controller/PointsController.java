package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.Points;
import com.aenlly.rcc.service.IPointsService;
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
 * 前端控制器
 *
 * @author aenlly
 * @since 2021-12-11
 */
@RestController
@RequestMapping("/points")
@Api(tags = "积分头衔管理控制器")
public class PointsController {
  @Resource private IPointsService pointsService;

  @ApiOperation("积分头衔列表")
  @GetMapping("/get")
  public CommonResult<List<Points>> getList() {
    try {
      List<Points> list = pointsService.list();
      list.sort(
          (o1, o2) -> {
            Integer integer = o1.getPointsRequire();
            Integer integer1 = o2.getPointsRequire();
            return integer.compareTo(integer1);
          });
      return resultOk(list);
    } catch (Exception e) {
      return resultErrorList();
    }
  }

  @ApiOperation("获得下一等级")
  @GetMapping("/getNextLevel/{points}")
  public CommonResult<Points> getNextLevel(
      @Param("当前累积积分") @PathVariable("points") Integer points) {
    try {
      Points entity = pointsService.getNextLevel(points);
      return resultOk(entity);
    } catch (Exception e) {
      return resultErrorOne();
    }
  }

  /**
   * 操作成功统一返回单个内容构造操作
   *
   * @param entity 单一实体内容
   * @return 返回内容
   */
  private CommonResult<Points> resultOk(Points entity) {
    return new CommonResult<>(CodeResult.OK, MessageResult.OK, entity);
  }

  /**
   * 操作成功统一返回列表内容构造操作
   *
   * @param list 列表实体内容
   * @return 返回内容
   */
  private CommonResult<List<Points>> resultOk(List<Points> list) {
    return new CommonResult<>(CodeResult.OK, MessageResult.OK, list);
  }

  /**
   * 操作失败执行方法
   *
   * @return 返回内容
   */
  private CommonResult<List<Points>> resultErrorList() {
    return new CommonResult<>(CodeResult.ERROR, MessageResult.ERROR, null);
  }

  /**
   * 操作失败执行方法
   *
   * @return 返回内容
   */
  private CommonResult<Points> resultErrorOne() {
    return new CommonResult<>(CodeResult.ERROR, MessageResult.ERROR, null);
  }
}
