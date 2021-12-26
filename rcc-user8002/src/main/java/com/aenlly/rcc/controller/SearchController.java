package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.GarbageLibrary;
import com.aenlly.rcc.entity.UserSearch;
import com.aenlly.rcc.enums.SearchTypeEnum;
import com.aenlly.rcc.utils.CommonResult;
import com.aenlly.rcc.utils.ISearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

  @Resource ISearchService searchService;

  @ApiOperation(value = "文本搜索垃圾所属分类请求", httpMethod = "GET")
  @GetMapping("/getSearchText")
  public CommonResult<Collection<GarbageLibrary>> getSearchText(
      @Param("垃圾名称") @RequestParam("name") String name,
      @Param("用户编号") @RequestParam("userId") String userId,
      @Param("搜索类型") @RequestParam("type") SearchTypeEnum typeEnum) {
    Collection<GarbageLibrary> list = searchService.searchText(name, userId, typeEnum);
    if (list != null) {
      return resultOk(list);
    }
    return resultError();
  }

  @ApiOperation(value = "首页-语音搜索识别请求", httpMethod = "POST")
  @PostMapping("/searchVoice")
  public CommonResult<String> searchVoice(
      @Param("语音文件") @RequestParam("voice") MultipartFile voice) {
    try {
      // String result = searchService.searchVoice(voice);
      return resultOk("苹果");
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "首页-图片搜索识别请求", httpMethod = "POST")
  @PostMapping("/searchPicture")
  public CommonResult<String> searchPicture(
      @Param("图片文件") @RequestParam("picture") MultipartFile picture) {
    try {
      String result = searchService.searchPicture(picture);
      return resultOk(result);
    } catch (Exception e) {
      return resultError();
    }
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
