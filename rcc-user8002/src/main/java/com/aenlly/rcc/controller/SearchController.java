package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.GarbageLibrary;
import com.aenlly.rcc.entity.UserSearch;
import com.aenlly.rcc.utils.CodeResult;
import com.aenlly.rcc.utils.CommonResult;
import com.aenlly.rcc.utils.MessageResult;
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

  @ApiOperation(value = "查询垃圾所属分类")
  @GetMapping("/getSearchText")
  public CommonResult<Collection<GarbageLibrary>> getSearchText(
      @Param("垃圾名称") @RequestParam("name") String name,
      @Param("用户编号") @RequestParam("userId") String userId) {
    Collection<GarbageLibrary> list = searchService.searchText(name, userId);
    if (list != null) {
      return resultOk(list);
    }
    return resultErrorCollect();
  }

  @ApiOperation(value = "查询用户所有搜索记录")
  @GetMapping("/get")
  public CommonResult<List<UserSearch>> getSearchList(
      @Param("用户编号") @RequestParam("userId") String userId) {
    try {
      List<UserSearch> list = searchService.getSearchList(userId);
      return resultOk(list);
    } catch (Exception e) {
      e.printStackTrace();
      return resultErrorList();
    }
  }

  @ApiOperation(value = "根据垃圾名称查询用户搜索记录")
  @GetMapping("/getSearchByName")
  public CommonResult<List<UserSearch>> getSearchByName(
      @Param("用户编号") @RequestParam("userId") String userId,
      @Param("搜索记录中的垃圾名称") @RequestParam("name") String name) {
    try {
      List<UserSearch> list = searchService.getSearchByName(userId, name);
      return resultOk(list);
    } catch (Exception e) {
      e.printStackTrace();
      return resultErrorList();
    }
  }

  /**
   * 操作成功统一返回列表内容构造操作执行方法
   *
   * @param list 列表内容
   * @return 返回内容
   */
  private CommonResult<Collection<GarbageLibrary>> resultOk(Collection<GarbageLibrary> list) {
    return new CommonResult<>(CodeResult.OK, MessageResult.OK, list);
  }

  /**
   * 操作失败执行方法
   *
   * @return 返回内容
   */
  private CommonResult<Collection<GarbageLibrary>> resultErrorCollect() {
    return new CommonResult<>(CodeResult.ERROR, MessageResult.ERROR, null);
  }

  /**
   * 操作成功统一返回列表内容构造操作执行方法
   *
   * @param list 列表内容
   * @return 返回内容
   */
  private CommonResult<List<UserSearch>> resultOk(List<UserSearch> list) {
    return new CommonResult<>(CodeResult.OK, MessageResult.OK, list);
  }

  /**
   * 操作失败执行方法
   *
   * @return 返回内容
   */
  private CommonResult<List<UserSearch>> resultErrorList() {
    return new CommonResult<>(CodeResult.ERROR, MessageResult.ERROR, null);
  }
}
