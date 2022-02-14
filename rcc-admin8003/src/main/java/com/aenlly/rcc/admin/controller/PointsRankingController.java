package com.aenlly.rcc.admin.controller;

import com.aenlly.rcc.admin.service.IPointsRankingService;
import com.aenlly.rcc.entity.PointsLog;
import com.aenlly.rcc.utils.CommonResult;
import com.aenlly.rcc.utils.enums.QueryPointsTypeEnum;
import com.aenlly.rcc.vo.UserVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
 * 积分排名查看控制器
 *
 * @author Aenlly
 * @create by date 2022/02/14 23:28
 * @projectName RefuseClassificationCultivate
 */
@RestController
@RequestMapping("/pointsRanking")
public class PointsRankingController {

  @Resource private IPointsRankingService service;

  @ApiOperation(value = "请求积分排名信息数据", httpMethod = "GET")
  @GetMapping("/getList")
  public CommonResult<IPage<UserVo>> getList(
      @Param("当前页码") @RequestParam("current") Integer current,
      @Param("每页数量") @RequestParam("size") Integer size,
      @Param("查询内容") @RequestParam("text") String text,
      @Param("积分类型") @RequestParam("pointsType") QueryPointsTypeEnum typeEnum) {
    try {
      IPage<UserVo> list = service.getList(new Page<>(current, size), text, typeEnum);
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
