package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.GiftListView;
import com.aenlly.rcc.service.IGiftService;
import com.aenlly.rcc.utils.CommonResult;
import com.aenlly.rcc.utils.enums.QueryGiftTypeEnum;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

import static com.aenlly.rcc.utils.ResultUtil.resultError;
import static com.aenlly.rcc.utils.ResultUtil.resultOk;

/**
 * 礼品信息表 前端控制器
 *
 * @author aenlly
 * @since 2022-01-17
 */
@RestController
@Api(tags = "礼品信息管理控制器")
@RequestMapping("/gift")
public class GiftController {

  @Resource private IGiftService service;

  @ApiOperation(value = "请求礼品信息数据")
  @GetMapping("/getList")
  public CommonResult<IPage<GiftListView>> getList(
      @ApiParam("当前页码") @RequestParam("current") Integer current,
      @ApiParam("每页数量") @RequestParam("size") Integer size,
      @ApiParam("查询类型") @RequestParam("queryType") QueryGiftTypeEnum queryType,
      @ApiParam("查询内容") @RequestParam("text") String text,
      @ApiParam("类型编号(非必须)") @RequestParam(value = "giftType", required = false) Long typeId) {
    try {
      IPage<GiftListView> list =
          service.getList(new Page<>(current, size), queryType, text, typeId);
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "根据id执行删除请求")
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

  @ApiOperation(value = "根据id集合执行批量删除请求")
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

  @ApiOperation(value = "上传封面请求")
  @PostMapping("/uploadImage")
  public CommonResult<String> uploadImage(
      @Param("封面文件") @RequestPart("imageFile") MultipartFile file) {
    try {
      String filePath = service.uploadImage(file);
      return resultOk(filePath);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "新增礼品信息请求")
  @PostMapping("/create")
  public CommonResult<Boolean> create(@Param("礼品信息") GiftListView entity) {
    try {
      Boolean save = service.create(entity);
      return resultOk(save);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "更新礼品信息请求")
  @PutMapping("/update")
  public CommonResult<Boolean> update(@Param("礼品信息") GiftListView entity) {
    try {
      Boolean save = service.update(entity);
      return resultOk(save);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "增加库存")
  @PutMapping("/addNumber")
  public CommonResult<Boolean> addNumber(
      @Param("主键") @RequestParam("id") Long id,
      @Param("增加的库存值") @RequestParam("number") Long number) {
    try {
      Boolean check = service.addNumber(id, number);
      return resultOk(check);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "减少库存")
  @PutMapping("/cutNumber")
  public CommonResult<Boolean> cutNumber(
      @Param("主键") @RequestParam("id") Long id,
      @Param("减少的库存值") @RequestParam("number") Long number) {
    try {
      Boolean check = service.cutNumber(id, number);
      return resultOk(check);
    } catch (Exception e) {
      return resultError();
    }
  }
}
