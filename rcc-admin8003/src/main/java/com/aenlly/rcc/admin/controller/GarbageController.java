package com.aenlly.rcc.admin.controller;

import com.aenlly.rcc.admin.service.IGarbageService;
import com.aenlly.rcc.entity.Garbage;
import com.aenlly.rcc.utils.CommonResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
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

  @ApiOperation(value = "请求垃圾类型信息数据", httpMethod = "GET")
  @GetMapping("/getList")
  public CommonResult<IPage<Garbage>> getList(
      @Param("当前页码") @RequestParam("current") Integer current,
      @Param("每页数量") @RequestParam("size") Integer size) {
    try {
      IPage<Garbage> list = service.getList(new Page<>(current, size));
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "上传视频请求", httpMethod = "POST")
  @PostMapping("/uploadVideo")
  public CommonResult<String> uploadVideo(
      @Param("视频文件") @RequestPart("videoFile") MultipartFile file) {
    try {
      String filePath = service.uploadVideo(file);
      return resultOk(filePath);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "上传图标请求", httpMethod = "POST")
  @PostMapping("/uploadImage")
  public CommonResult<String> uploadImage(
      @Param("轮播图") @RequestPart("imageFile") MultipartFile file) {
    try {
      String filePath = service.uploadImage(file);
      return resultOk(filePath);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "更新垃圾类型信息请求", httpMethod = "PUT")
  @PutMapping("/update")
  public CommonResult<Boolean> update(@Param("轮播信息") Garbage entity) {
    try {
      Boolean save = service.update(entity);
      return resultOk(save);
    } catch (Exception e) {
      return resultError();
    }
  }
}
