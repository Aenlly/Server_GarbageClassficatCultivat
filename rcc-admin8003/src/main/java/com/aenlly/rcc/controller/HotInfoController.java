package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.HotInfo;
import com.aenlly.rcc.enums.HotInfoStateEnum;
import com.aenlly.rcc.service.IHotInfoService;
import com.aenlly.rcc.utils.CommonResult;
import com.aenlly.rcc.utils.enums.QueryHotInfoTypeEnum;
import com.aenlly.rcc.vo.HotInfoVo;
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
 * 热门资讯表 前端控制器
 *
 * @author aenlly
 * @since 2022-02-03
 */
@RestController
@Api(tags = "热门资讯管理控制器")
@RequestMapping("/hot-info")
public class HotInfoController {

  @Resource private IHotInfoService service;

  @ApiOperation(value = "请求热门资讯信息数据")
  @GetMapping("/getList")
  public CommonResult<IPage<HotInfoVo>> getList(
      @ApiParam("当前页码") @RequestParam("current") Integer current,
      @ApiParam("每页数量") @RequestParam("size") Integer size,
      @ApiParam("查询类型") @RequestParam("queryType") QueryHotInfoTypeEnum queryType,
      @ApiParam("查询内容") @RequestParam("text") String text,
      @ApiParam("信息状态(非必须)") @RequestParam(value = "state", required = false) HotInfoStateEnum state) {
    try {
      IPage<HotInfoVo> list = service.getList(new Page<>(current, size), queryType, text, state);
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "根据id请求热门资讯信息数据")
  @GetMapping("/getById/{id}")
  public CommonResult<HotInfo> getById(@ApiParam("资讯编号") @PathVariable("id") Long id) {
    try {
      HotInfo hotInfo = service.getById(id);
      return resultOk(hotInfo);
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
        return resultOk(true);
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
      Boolean check = service.updateByIdCheck(id, HotInfoStateEnum.PUBLISH_OK);
      return resultOk(check);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "根据id下线数据请求")
  @PutMapping("/shelf/{id}")
  public CommonResult<Boolean> shelf(@Param("主键") @PathVariable("id") Long id) {
    try {
      Boolean check = service.updateByIdCheck(id, HotInfoStateEnum.PUBLISH_NOT);
      return resultOk(check);
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

  @ApiOperation(value = "新增热门资讯信息请求")
  @PostMapping("/create")
  public CommonResult<Boolean> create(@Param("热门资讯") HotInfo entity) {
    try {
      Boolean save = service.create(entity);
      return resultOk(save);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "编辑更新热门资讯信息请求")
  @PutMapping("/update")
  public CommonResult<Boolean> update(@Param("热门资讯") HotInfo entity) {
    try {
      Boolean save = service.update(entity);
      return resultOk(save);
    } catch (Exception e) {
      return resultError();
    }
  }
}
