package com.aenlly.rcc.admin.controller;

import com.aenlly.rcc.admin.service.ICarouselService;
import com.aenlly.rcc.entity.Carousel;
import com.aenlly.rcc.enums.CarouselShowFlagEnum;
import com.aenlly.rcc.utils.CommonResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

import static com.aenlly.rcc.utils.ResultUtil.*;

/**
 * 轮播图表 前端控制器
 *
 * @author aenlly
 * @since 2022-01-14
 */
@RestController
@Api(tags = "轮播信息管理控制器")
@RequestMapping("/carousel")
public class CarouselController {

  /** 轮播信息表服务对象 */
  @Resource private ICarouselService service;

  @ApiOperation(value = "请求轮播信息数据", httpMethod = "GET")
  @GetMapping("/getList")
  public CommonResult<IPage<Carousel>> getList(
      @Param("当前页码") @RequestParam("current") Integer current,
      @Param("每页数量") @RequestParam("size") Integer size,
      @Param("查询内容") @RequestParam("text") String text) {
    try {
      IPage<Carousel> list = service.getList(new Page<>(current, size), text);
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "根据id执行删除请求", httpMethod = "DELETE")
  @DeleteMapping("/delById")
  public CommonResult<Boolean> delById(@Param("主键") @RequestParam("id") Long id) {
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

  @ApiOperation(value = "根据id集合执行批量删除请求", httpMethod = "DELETE")
  @DeleteMapping("/delByIds")
  public CommonResult<Boolean> delByIds(@Param("主键集合") @RequestBody List<Long> ids) {
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

  @ApiOperation(value = "根据id发布数据请求", httpMethod = "PUT")
  @PutMapping("/publish/{id}")
  public CommonResult<Boolean> publish(@Param("主键") @PathVariable("id") Long id) {
    try {
      Boolean check = service.putOnline(id, CarouselShowFlagEnum.PUBLISH);
      return resultOk(check);
    } catch (ArrayIndexOutOfBoundsException e) {
      return resultExceed();
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "根据id下线数据请求", httpMethod = "PUT")
  @PutMapping("/offline/{id}")
  public CommonResult<Boolean> offline(@Param("主键") @PathVariable("id") Long id) {
    try {
      Boolean check = service.putOffline(id, CarouselShowFlagEnum.OFFLINE);
      return resultOk(check);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "上传轮播图请求", httpMethod = "POST")
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

  @ApiOperation(value = "新增轮播图信息请求", httpMethod = "POST")
  @PostMapping("/create")
  public CommonResult<Boolean> create(@Param("轮播信息") Carousel entity) {
    try {
      Boolean save = service.create(entity);
      return resultOk(save);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "更新轮播信息请求", httpMethod = "PUT")
  @PutMapping("/update")
  public CommonResult<Boolean> update(@Param("轮播信息") Carousel entity) {
    try {
      Boolean save = service.update(entity);
      return resultOk(save);
    } catch (Exception e) {
      return resultError();
    }
  }
}
