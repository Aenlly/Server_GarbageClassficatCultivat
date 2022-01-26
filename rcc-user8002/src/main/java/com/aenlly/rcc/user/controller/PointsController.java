package com.aenlly.rcc.user.controller;

import com.aenlly.rcc.entity.Points;
import com.aenlly.rcc.user.service.IPointsService;
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
 * 前端控制器
 *
 * @author aenlly
 * @since 2021-12-11
 */
@RestController
@Api(tags = "积分头衔管理控制器")
@RequestMapping("/points")
public class PointsController {
  @Resource private IPointsService pointsService;

  @ApiOperation(value = "查询积分头衔列表", httpMethod = "GET")
  @GetMapping("/get")
  public CommonResult<List<Points>> getList() {
    try {
      List<Points> list = pointsService.list();
      list.sort(
          (o1, o2) -> {
            Long integer = o1.getPointsRequire();
            Long integer1 = o2.getPointsRequire();
            return integer.compareTo(integer1);
          });
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "查询积分头衔下一等级", httpMethod = "GET")
  @GetMapping("/getNextLevel/{points}")
  public CommonResult<Points> getNextLevel(
      @Param("当前累积积分") @PathVariable("points") Integer points) {
    try {
      Points entity = pointsService.getNextLevel(points);
      return resultOk(entity);
    } catch (Exception e) {
      return resultError();
    }
  }
}
