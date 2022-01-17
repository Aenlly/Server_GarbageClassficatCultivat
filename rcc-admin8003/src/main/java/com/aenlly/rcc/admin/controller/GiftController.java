package com.aenlly.rcc.admin.controller;

import com.aenlly.rcc.admin.service.IGiftService;
import com.aenlly.rcc.entity.GiftListView;
import com.aenlly.rcc.utils.CommonResult;
import com.aenlly.rcc.utils.enums.QueryGiftTypeEnum;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

  @ApiOperation(value = "请求礼品信息数据", httpMethod = "GET")
  @GetMapping("/getList")
  public CommonResult<IPage<GiftListView>> getList(
      @Param("当前页码") @RequestParam("current") Integer current,
      @Param("每页数量") @RequestParam("size") Integer size,
      @Param("查询类型") @RequestParam("queryType") QueryGiftTypeEnum queryType,
      @Param("查询内容") @RequestParam("text") String text,
      @Param("核销状态(非必须)") @RequestParam(value = "giftType", required = false) Long typeId) {
    try {
      IPage<GiftListView> list =
          service.getList(new Page<>(current, size), queryType, text, typeId);
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
  public CommonResult<Boolean> delVideoByIds(@Param("主键集合") @RequestBody List<Long> ids) {
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

  @ApiOperation(value = "上传封面请求", httpMethod = "POST")
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

  @ApiOperation(value = "新增礼品信息请求", httpMethod = "POST")
  @PostMapping("/create")
  public CommonResult<Boolean> create(@Param("视频信息") GiftListView entity) {
    try {
      Boolean save = service.create(entity);
      return resultOk(save);
    } catch (Exception e) {
      return resultError();
    }
  }
}
