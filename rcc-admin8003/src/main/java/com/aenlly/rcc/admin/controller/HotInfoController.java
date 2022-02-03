package com.aenlly.rcc.admin.controller;

import com.aenlly.rcc.admin.service.IHotInfoService;
import com.aenlly.rcc.entity.HotInfo;
import com.aenlly.rcc.enums.HotInfoStateEnum;
import com.aenlly.rcc.utils.CommonResult;
import com.aenlly.rcc.utils.enums.QueryHotInfoTypeEnum;
import com.aenlly.rcc.vo.HotInfoVo;
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
 * 热门资讯表 前端控制器
 *
 * @author aenlly
 * @since 2022-02-03
 */
@RestController
@Api("热门资讯管理控制器")
@RequestMapping("/hot-info")
public class HotInfoController {

  @Resource private IHotInfoService service;

  @ApiOperation(value = "请求热门资讯信息数据", httpMethod = "GET")
  @GetMapping("/getList")
  public CommonResult<IPage<HotInfoVo>> getList(
      @Param("当前页码") @RequestParam("current") Integer current,
      @Param("每页数量") @RequestParam("size") Integer size,
      @Param("查询类型") @RequestParam("queryType") QueryHotInfoTypeEnum queryType,
      @Param("查询内容") @RequestParam("text") String text,
      @Param("信息状态(非必须)") @RequestParam(value = "state", required = false) HotInfoStateEnum state) {
    try {
      IPage<HotInfoVo> list = service.getList(new Page<>(current, size), queryType, text, state);
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "根据id请求热门资讯信息数据", httpMethod = "GET")
  @GetMapping("/getById/{id}")
  public CommonResult<HotInfo> getById(@Param("资讯编号") @PathVariable("id") Long id) {
    try {
      HotInfo hotInfo = service.getById(id);
      return resultOk(hotInfo);
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
        return resultOk(true);
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
      Boolean check = service.updateByIdCheck(id, HotInfoStateEnum.PUBLISH_OK);
      return resultOk(check);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "根据id下线数据请求", httpMethod = "PUT")
  @PutMapping("/shelf/{id}")
  public CommonResult<Boolean> shelf(@Param("主键") @PathVariable("id") Long id) {
    try {
      Boolean check = service.updateByIdCheck(id, HotInfoStateEnum.PUBLISH_NOT);
      return resultOk(check);
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
}
