package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.Video;
import com.aenlly.rcc.enums.VideoCheckEnum;
import com.aenlly.rcc.service.IVideoService;
import com.aenlly.rcc.utils.CommonResult;
import com.aenlly.rcc.utils.enums.QueryVideoTypeEnum;
import com.aenlly.rcc.vo.VideoVo;
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
 * 公益视频信息管理 前端控制器
 *
 * @author aenlly
 * @since 2022-01-11
 */
@RestController
@Api(tags = "公益视频信息管理控制器")
@RequestMapping("/video")
public class VideoController {
  /** 视频信息表服务类 */
  @Resource private IVideoService service;

  @ApiOperation(value = "请求视频信息数据据")
  @GetMapping("/getList")
  public CommonResult<IPage<VideoVo>> getVideoList(
      @ApiParam("当前页码") @RequestParam("current") Integer current,
      @ApiParam("每页数量") @RequestParam("size") Integer size,
      @ApiParam("查询类型") @RequestParam("queryType") QueryVideoTypeEnum queryType,
      @ApiParam("查询内容") @RequestParam("text") String text) {
    try {
        IPage<VideoVo> list = service.getList(new Page<>(current, size), queryType, text);
        return resultOk(list);
    } catch (Exception e) {
        e.printStackTrace();
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
      Boolean check = service.updateByIdCheck(id, VideoCheckEnum.PUBLISH_OK);
      return resultOk(check);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "根据id下线数据请求")
  @PutMapping("/shelf/{id}")
  public CommonResult<Boolean> shelf(@ApiParam("主键") @PathVariable("id") Long id) {
    try {
      Boolean check = service.updateByIdCheck(id, VideoCheckEnum.PUBLISH_NOT);
      return resultOk(check);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "根据id置顶数据请求")
  @PutMapping("/top/{id}")
  public CommonResult<Boolean> top(@ApiParam("主键") @PathVariable("id") Long id) {
    try {
      Boolean check = service.updateByIdCheck(id, VideoCheckEnum.TOP);
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

  @ApiOperation(value = "新增公益视频信息请求")
  @PostMapping("/create")
  public CommonResult<Boolean> create(@ApiParam("视频信息") Video video) {
    try {
      Boolean save = service.create(video);
      return resultOk(save);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "编辑公益视频信息请求")
  @PutMapping("/update")
  public CommonResult<Boolean> update(@ApiParam("视频信息") Video video) {
    try {
      Boolean save = service.update(video);
      return resultOk(save);
    } catch (Exception e) {
      return resultError();
    }
  }
}
