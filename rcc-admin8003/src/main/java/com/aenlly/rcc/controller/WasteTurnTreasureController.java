package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.WasteTurnTreasure;
import com.aenlly.rcc.enums.AuditEnum;
import com.aenlly.rcc.enums.IsUserUploadEnum;
import com.aenlly.rcc.enums.WasteTagEnum;
import com.aenlly.rcc.service.IWasteTurnTreasureService;
import com.aenlly.rcc.utils.CommonResult;
import com.aenlly.rcc.utils.enums.QueryWasteTypeEnum;
import com.aenlly.rcc.vo.WasteTurnTreasureVo;
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
 * 变废为宝表 前端控制器
 *
 * @author aenlly
 * @since 2022-01-31
 */
@RestController
@Api(tags = "变废为宝管理控制器")
@RequestMapping("/waste-turn-treasure")
public class WasteTurnTreasureController {

  @Resource private IWasteTurnTreasureService service;

  @ApiOperation(value = "请求已发与已下架的变废为宝信息数据")
  @GetMapping("/getList")
  public CommonResult<IPage<WasteTurnTreasureVo>> getList(
      @ApiParam("当前页码") @RequestParam("current") Integer current,
      @ApiParam("每页数量") @RequestParam("size") Integer size,
      @ApiParam("查询类型") @RequestParam("queryType") QueryWasteTypeEnum queryType,
      @ApiParam("查询内容") @RequestParam("text") String text,
      @ApiParam("标签(非必须)") @RequestParam(value = "textTag", required = false) WasteTagEnum textTag,
      @ApiParam("是否用户上传") @RequestParam("isUserUpload") IsUserUploadEnum isUserUploadEnum) {
    try {
      IPage<WasteTurnTreasureVo> list =
          service.getList(
              new Page<>(current, size),
              queryType,
              text,
              textTag,
              isUserUploadEnum,
              List.of(AuditEnum.THROUGH, AuditEnum.OFF));
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "请求待审核变废为宝信息数据")
  @GetMapping("/getAuditList")
  public CommonResult<IPage<WasteTurnTreasureVo>> getAuditList(
      @ApiParam("当前页码") @RequestParam("current") Integer current,
      @ApiParam("每页数量") @RequestParam("size") Integer size,
      @ApiParam("查询类型") @RequestParam("queryType") QueryWasteTypeEnum queryType,
      @ApiParam("查询内容") @RequestParam("text") String text,
      @ApiParam("标签(非必须)") @RequestParam(value = "textTag", required = false) WasteTagEnum textTag,
      @ApiParam("是否用户上传") @RequestParam("isUserUpload") IsUserUploadEnum isUserUploadEnum) {
    try {
      IPage<WasteTurnTreasureVo> list =
          service.getList(
              new Page<>(current, size),
              queryType,
              text,
              textTag,
              isUserUploadEnum,
              List.of(AuditEnum.TO_AUDIT));
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "请求未通过的变废为宝信息数据")
  @GetMapping("/getNotThroughList")
  public CommonResult<IPage<WasteTurnTreasureVo>> getNotThroughList(
      @ApiParam("当前页码") @RequestParam("current") Integer current,
      @ApiParam("每页数量") @RequestParam("size") Integer size,
      @ApiParam("查询类型") @RequestParam("queryType") QueryWasteTypeEnum queryType,
      @ApiParam("查询内容") @RequestParam("text") String text,
      @ApiParam("标签(非必须)") @RequestParam(value = "textTag", required = false) WasteTagEnum textTag,
      @ApiParam("是否用户上传") @RequestParam("isUserUpload") IsUserUploadEnum isUserUploadEnum) {
    try {
      IPage<WasteTurnTreasureVo> list =
          service.getList(
              new Page<>(current, size),
              queryType,
              text,
              textTag,
              isUserUploadEnum,
              List.of(AuditEnum.Not_THROUGH));
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

  @ApiOperation(value = "根据id发布数据请求")
  @PutMapping("/publish/{id}")
  public CommonResult<Boolean> publish(@ApiParam("主键") @PathVariable("id") Long id) {
    try {
      Boolean check = service.updateByIdRevise(id, AuditEnum.THROUGH);
      return resultOk(check);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "根据id审核数据不通过")
  @PutMapping("/notThrough/{id}")
  public CommonResult<Boolean> notThrough(@ApiParam("主键") @PathVariable("id") Long id) {
    try {
      Boolean check = service.updateByIdRevise(id, AuditEnum.Not_THROUGH);
      return resultOk(check);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "根据id下架数据请求")
  @PutMapping("/shelf/{id}")
  public CommonResult<Boolean> shelf(@ApiParam("主键") @PathVariable("id") Long id) {
    try {
      Boolean check = service.updateByIdRevise(id, AuditEnum.OFF);
      return resultOk(check);
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

  @ApiOperation(value = "上传封面请求")
  @PostMapping("/uploadImage")
  public CommonResult<String> uploadImage(
      @ApiParam("封面文件") @RequestPart("imageFile") MultipartFile file) {
    try {
      String filePath = service.uploadImage(file);
      return resultOk(filePath);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "新增变废为宝信息请求")
  @PostMapping("/create")
  public CommonResult<Boolean> create(@ApiParam("变废为宝信息") WasteTurnTreasure entity) {
    try {
      Boolean save = service.create(entity);
      return resultOk(save);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "编辑变废为宝信息请求")
  @PutMapping("/update")
  public CommonResult<Boolean> update(@ApiParam("变废为宝信息") WasteTurnTreasure entity) {
    try {
      Boolean save = service.update(entity);
      return resultOk(save);
    } catch (Exception e) {
      return resultError();
    }
  }
}
