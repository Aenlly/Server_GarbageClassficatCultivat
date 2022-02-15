package com.aenlly.rcc.admin.controller;

import com.aenlly.rcc.admin.service.IStatisticsService;
import com.aenlly.rcc.utils.CommonResult;
import com.aenlly.rcc.utils.enums.QueryTimeTypeEnum;
import com.aenlly.rcc.vo.StatisticsVo;
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
 * 数据信息统计控制器
 *
 * @author Aenlly
 * @create by date 2022/02/15 13:38
 * @projectName RefuseClassificationCultivate
 */
@RestController
@Api(tags = "数据统计信息控制器")
@RequestMapping("/statistics")
public class StatisticsController {

  @Resource private IStatisticsService service;

  @ApiOperation(value = "根据时间统计范围请求统计数据", httpMethod = "GET")
  @GetMapping("/getInit")
  public CommonResult<StatisticsVo> getInit(
      @Param("时间统计范围") @RequestParam("timeType") QueryTimeTypeEnum typeEnum) {
    try {
      StatisticsVo list = service.getInit(typeEnum);
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }
}
