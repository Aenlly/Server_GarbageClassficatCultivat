package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.GiftType;
import com.aenlly.rcc.service.IGiftTypeService;
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
 * 礼品类型表 前端控制器
 *
 * @author aenlly
 * @since 2022-01-17
 */
@RestController
@Api(tags = "礼品类型管理控制器")
@RequestMapping("/gift-type")
public class GiftTypeController {

  @Resource private IGiftTypeService service;

  @ApiOperation(value = "根据类型名称请求列表数据,用于礼品查询")
  @GetMapping("/getSelectListBy")
  public CommonResult<List<GiftType>> getSelectListBy(
      @ApiParam("类型名称") @RequestParam("text") String text) {
    try {
      List<GiftType> list = service.getSelectListBy(text);
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "请求礼品类型数据")
  @GetMapping("/getList")
  public CommonResult<IPage<GiftType>> getList(
      @ApiParam("当前页码") @RequestParam("current") Integer current,
      @ApiParam("每页数量") @RequestParam("size") Integer size,
      @ApiParam("查询内容") @RequestParam("text") String text) {
    try {
      IPage<GiftType> list = service.getList(new Page<>(current, size), text);
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

  @ApiOperation(value = "上传图片请求")
  @PostMapping("/uploadImage")
  public CommonResult<String> uploadImage(
      @ApiParam("礼品类型图片") @RequestPart("imageFile") MultipartFile file) {
    try {
      String filePath = service.uploadImage(file);
      return resultOk(filePath);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "新增礼品类型信息请求")
  @PostMapping("/create")
  public CommonResult<Boolean> create(@ApiParam("礼品信息") GiftType entity) {
    try {
      Boolean save = service.create(entity);
      return resultOk(save);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "更新礼品类型信息请求")
  @PutMapping("/update")
  public CommonResult<Boolean> update(@ApiParam("礼品信息") GiftType entity) {
    try {
      Boolean save = service.update(entity);
      return resultOk(save);
    } catch (Exception e) {
      return resultError();
    }
  }
}
