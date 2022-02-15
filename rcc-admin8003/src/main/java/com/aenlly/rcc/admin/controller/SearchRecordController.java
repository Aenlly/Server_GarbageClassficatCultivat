package com.aenlly.rcc.admin.controller;

import com.aenlly.rcc.admin.service.ISearchRecordService;
import com.aenlly.rcc.entity.SearchUser;
import com.aenlly.rcc.enums.SearchTypeEnum;
import com.aenlly.rcc.utils.CommonResult;
import com.aenlly.rcc.vo.SearchNameChartVo;
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
import java.util.List;
import java.util.Map;

import static com.aenlly.rcc.utils.ResultUtil.resultError;
import static com.aenlly.rcc.utils.ResultUtil.resultOk;

/**
 * 用户搜索记录表 前端控制器
 *
 * @author aenlly
 * @since 2022-01-26
 */
@RestController
@Api(tags = "搜索记录查看控制器")
@RequestMapping("/search-record")
public class SearchRecordController {

  @Resource private ISearchRecordService service;

  @ApiOperation(value = "请求搜索记录信息数据", httpMethod = "GET")
  @GetMapping("/getList")
  public CommonResult<IPage<SearchUser>> getList(
      @Param("当前页码") @RequestParam("current") Integer current,
      @Param("每页数量") @RequestParam("size") Integer size,
      @Param("查询的搜索类型(非必须)") @RequestParam(value = "type", required = false)
          SearchTypeEnum typeEnum) {
    try {
      IPage<SearchUser> list = service.getList(new Page<>(current, size), typeEnum);
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "获取搜索类型集合", httpMethod = "GET")
  @GetMapping("/getListType")
  public CommonResult<List<SearchTypeEnum>> getListType() {
    try {
      List<SearchTypeEnum> list = service.getType();
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "获取搜索记录图表型数据", httpMethod = "GET")
  @GetMapping("/getSearchChart")
  public CommonResult<List<Map<String, SearchNameChartVo>>> getSearchChart() {
    try {
      List<Map<String, SearchNameChartVo>> list = service.getSearchChart();
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }
}
