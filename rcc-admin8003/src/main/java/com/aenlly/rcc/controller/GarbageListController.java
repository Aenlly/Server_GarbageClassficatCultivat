package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.GarbageList;
import com.aenlly.rcc.service.IGarbageListService;
import com.aenlly.rcc.utils.CommonResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

import static com.aenlly.rcc.utils.ResultUtil.resultError;
import static com.aenlly.rcc.utils.ResultUtil.resultOk;

/**
 * 所属垃圾类型信息条目表 前端控制器
 *
 * @author aenlly
 * @since 2022-01-15
 */
@RestController
@Api(tags = "所属垃圾类型信息管理控制器")
@RequestMapping("/garbage-list")
public class GarbageListController {

  /** 所属垃圾类型条目信息表服务对象 */
  @Resource private IGarbageListService service;

  @ApiOperation(value = "请求所属垃圾类型条目信息数据")
  @GetMapping("/getList")
  public CommonResult<IPage<GarbageList>> getList(
      @ApiParam("当前页码") @RequestParam("current") Integer current,
      @ApiParam("每页数量") @RequestParam("size") Integer size,
      @ApiParam("所属垃圾类型Id") @RequestParam("belongId") Integer belongId,
      @ApiParam("查询内容") @RequestParam("text") String text) {
    try {
      IPage<GarbageList> list = service.getList(new Page<>(current, size), belongId, text);
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

  @ApiOperation(value = "上传垃圾图标请求")
  @PostMapping("/uploadImage")
  public CommonResult<String> uploadImage(
      @ApiParam("垃圾类条目图标") @RequestPart("imageFile") MultipartFile file) {
    try {
      String filePath = service.uploadImage(file);
      return resultOk(filePath);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "新增所属垃圾类型条目信息请求")
  @PostMapping("/create")
  public CommonResult<Boolean> create(@ApiParam("垃圾类条目信息") GarbageList entity) {
    try {
      Boolean save = service.create(entity);
      return resultOk(save);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "更新所属垃圾类型条目信息请求")
  @PutMapping("/update")
  public CommonResult<Boolean> update(@ApiParam("垃圾类条目信息") GarbageList entity) {
    try {
      Boolean save = service.update(entity);
      return resultOk(save);
    } catch (Exception e) {
      return resultError();
    }
  }
}
