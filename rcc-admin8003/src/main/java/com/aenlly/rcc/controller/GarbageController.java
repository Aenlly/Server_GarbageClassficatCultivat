package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.Garbage;
import com.aenlly.rcc.service.IGarbageService;
import com.aenlly.rcc.utils.CommonResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import static com.aenlly.rcc.utils.ResultUtil.resultError;
import static com.aenlly.rcc.utils.ResultUtil.resultOk;

/**
 * 垃圾类型信息表 前端控制器
 *
 * @author aenlly
 * @since 2022-01-14
 */
@RestController
@Api(tags = "垃圾类型信息管理控制器")
@RequestMapping("/garbage")
public class GarbageController {

  @Resource private IGarbageService service;

  @ApiOperation(value = "请求垃圾类型信息数据")
  @GetMapping("/getList")
  public CommonResult<IPage<Garbage>> getList(
      @ApiParam("当前页码") @RequestParam("current") Integer current,
      @ApiParam("每页数量") @RequestParam("size") Integer size) {
    try {
      IPage<Garbage> list = service.getList(new Page<>(current, size));
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "上传视频请求")
  @PostMapping("/uploadVideo")
  public CommonResult<String> uploadVideo(
      @ApiParam("视频文件") @RequestPart("videoFile") MultipartFile file) {
    try {
      String filePath = service.uploadVideo(file);
      return resultOk(filePath);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "上传图标请求")
  @PostMapping("/uploadImage")
  public CommonResult<String> uploadImage(
      @ApiParam("垃圾类型图标") @RequestPart("imageFile") MultipartFile file) {
    try {
      String filePath = service.uploadImage(file);
      return resultOk(filePath);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "更新垃圾类型信息请求")
  @PutMapping("/update")
  public CommonResult<Boolean> update(@ApiParam("垃圾类型信息") Garbage entity) {
    try {
      Boolean save = service.update(entity);
      return resultOk(save);
    } catch (Exception e) {
      return resultError();
    }
  }
}
