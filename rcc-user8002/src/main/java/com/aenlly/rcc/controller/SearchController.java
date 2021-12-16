package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.GarbageLibrary;
import com.aenlly.rcc.entity.UserSearch;
import com.aenlly.rcc.utils.CommonResult;
import com.aenlly.rcc.utils.SearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static com.aenlly.rcc.utils.ResultUtil.resultError;
import static com.aenlly.rcc.utils.ResultUtil.resultOk;

/**
 * 前端控制器
 *
 * @author aenlly
 * @since 2021-12-14
 */
@RestController
@RequestMapping("/search")
@Api(tags = "用户首页-垃圾搜索管理控制器")
public class SearchController {

  @Resource SearchService searchService;

  @ApiOperation(value = "文本搜索垃圾所属分类请求", httpMethod = "GET")
  @GetMapping("/getSearchText")
  public CommonResult<Collection<GarbageLibrary>> getSearchText(
      @Param("垃圾名称") @RequestParam("name") String name,
      @Param("用户编号") @RequestParam("userId") String userId) {
    Collection<GarbageLibrary> list = searchService.searchText(name, userId);
    if (list != null) {
      return resultOk(list);
    }
    return resultError();
  }

  @ApiOperation(value = "用户所有搜索记录查询", httpMethod = "GET")
  @GetMapping("/get")
  public CommonResult<List<UserSearch>> getSearchList(
      @Param("用户编号") @RequestParam("userId") String userId) {
    try {
      List<UserSearch> list = searchService.getSearchList(userId);
      return resultOk(list);
    } catch (Exception e) {
      e.printStackTrace();
      return resultError();
    }
  }

  @ApiOperation(value = "用户搜索记录条件查询", httpMethod = "GET")
  @GetMapping("/getSearchByName")
  public CommonResult<List<UserSearch>> getSearchByName(
      @Param("用户编号") @RequestParam("userId") String userId,
      @Param("搜索记录中的垃圾名称") @RequestParam("name") String name) {
    try {
      List<UserSearch> list = searchService.getSearchByName(userId, name);
      return resultOk(list);
    } catch (Exception e) {
      e.printStackTrace();
      return resultError();
    }
  }
}
