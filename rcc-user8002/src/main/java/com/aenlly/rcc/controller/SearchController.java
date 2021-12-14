package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.GarbageLibrary;
import com.aenlly.rcc.utils.CommonResult;
import com.aenlly.rcc.utils.SearchService;
import io.swagger.annotations.Api;
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
 * @since 2021-12-14
 */
@RestController
@RequestMapping("/search")
@Api(tags = "用户首页-垃圾搜索控制器")
public class SearchController {

  @Resource SearchService searchService;

  @GetMapping("/getSearchText/{name}/{userId}")
  public CommonResult<List<GarbageLibrary>> getSearchText(
      @Param("垃圾名称") @PathVariable("name") String name,
      @Param("用户编号") @PathVariable("userId") String userId) {
    searchService.searchText(name, userId);
    return null;
  }
}
