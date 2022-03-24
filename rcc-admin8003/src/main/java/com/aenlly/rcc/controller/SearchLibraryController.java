package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.SearchLibrary;
import com.aenlly.rcc.enums.GarbageTypeEnum;
import com.aenlly.rcc.service.ISearchTextLibraryService;
import com.aenlly.rcc.utils.CommonResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.aenlly.rcc.utils.ResultUtil.resultError;
import static com.aenlly.rcc.utils.ResultUtil.resultOk;

/**
 * 搜索信息库控制器
 *
 * @author Aenlly
 * @create by date 2022/01/30 0:19
 * @projectName RefuseClassificationCultivate
 */
@RestController
@Api(tags = "搜索信息库控制器")
@RequestMapping("/search-library")
public class SearchLibraryController {

  @Resource private ISearchTextLibraryService service;

  @ApiOperation(value = "请求搜索信息库数据")
  @GetMapping("/getList")
  public CommonResult<IPage<SearchLibrary>> getList(
      @ApiParam("当前页码") @RequestParam("current") Integer current,
      @ApiParam("每页数量") @RequestParam("size") Integer size,
      @ApiParam("搜索内容") @RequestParam("text") String text) {
    try {
      IPage<SearchLibrary> list = service.getList(new Page<>(current, size), text);
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "获取搜索类型集合")
  @GetMapping("/getListType")
  public CommonResult<GarbageTypeEnum[]> getListType() {
    try {
      GarbageTypeEnum[] list = service.getType();
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "根据id执行删除请求")
  @DeleteMapping("/delById")
  public CommonResult<Boolean> delById(@ApiParam("主键") @RequestParam("id") Long id) {
    try {
      boolean b = service.removeById(id);
      if (b) {
        return resultOk(b);
      } else {
        throw new NullPointerException();
      }
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "根据id集合执行批量删除请求")
  @DeleteMapping("/delByIds")
  public CommonResult<Boolean> delByIds(@ApiParam("主键集合") @RequestBody List<Long> ids) {
    try {
      boolean b = service.removeByIds(ids);
      if (b) {
        return resultOk(true);
      } else {
        throw new NullPointerException();
      }
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "新增搜索库信息请求")
  @PostMapping("/create")
  public CommonResult<Boolean> create(@ApiParam("搜索库信息") SearchLibrary entity) {
    try {
      Boolean save = service.save(entity);
      return resultOk(save);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "更新搜索库信息请求")
  @PutMapping("/update")
  public CommonResult<Boolean> update(@ApiParam("搜索库信息") SearchLibrary entity) {
    try {
      Boolean save = service.updateById(entity);
      return resultOk(save);
    } catch (Exception e) {
      return resultError();
    }
  }
}
