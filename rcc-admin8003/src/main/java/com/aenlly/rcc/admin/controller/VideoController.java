package com.aenlly.rcc.admin.controller;

import com.aenlly.rcc.admin.service.IVideoService;
import com.aenlly.rcc.entity.Video;
import com.aenlly.rcc.enums.VideoCheckEnum;
import com.aenlly.rcc.utils.CommonResult;
import com.aenlly.rcc.utils.enums.QueryVideoType;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
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
@ApiOperation("公益视频信息管理控制器")
@RequestMapping("/video")
public class VideoController {
  /** 视频信息表服务类 */
  @Resource private IVideoService videoService;

  @ApiOperation(value = "请求视频信息数据据", httpMethod = "GET")
  @GetMapping("/getList")
  public CommonResult<IPage<Video>> getVideoList(
      @Param("当前页码") @RequestParam("current") Integer current,
      @Param("每页数量") @RequestParam("size") Integer size,
      @Param("查询类型") @RequestParam("queryType") QueryVideoType queryType,
      @Param("查询内容") @RequestParam("text") String text) {
    try {
      IPage<Video> list = videoService.getList(new Page<>(current, size), queryType, text);
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "根据id执行删除请求", httpMethod = "DELETE")
  @DeleteMapping("/delById")
  public CommonResult<Boolean> delById(@Param("主键") @RequestParam("id") Long id) {
    try {
      boolean b = videoService.removeById(id);
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
      boolean b = videoService.removeByIds(ids);
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
      Boolean check = videoService.updateByIdCheck(id, VideoCheckEnum.PUBLISH_OK);
      return resultOk(check);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "根据id下线数据请求", httpMethod = "PUT")
  @PutMapping("/shelf/{id}")
  public CommonResult<Boolean> shelf(@Param("主键") @PathVariable("id") Long id) {
    try {
      Boolean check = videoService.updateByIdCheck(id, VideoCheckEnum.PUBLISH_NOT);
      return resultOk(check);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "根据id置顶数据请求", httpMethod = "PUT")
  @PutMapping("/top/{id}")
  public CommonResult<Boolean> top(@Param("主键") @PathVariable("id") Long id) {
    try {
      Boolean check = videoService.updateByIdCheck(id, VideoCheckEnum.TOP);
      return resultOk(check);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "上传视频请求", httpMethod = "POST")
  @PostMapping("/uploadVideo")
  public CommonResult<String> uploadVideo(
      @Param("视频文件") @RequestPart("videoFile") MultipartFile file) {
    try {
      String filePath = videoService.uploadVideo(file);
      return resultOk(filePath);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "上传封面请求", httpMethod = "POST")
  @PostMapping("/uploadImage")
  public CommonResult<String> uploadImage(
      @Param("封面文件") @RequestPart("imageFile") MultipartFile file) {
    try {
      String filePath = videoService.uploadImage(file);
      return resultOk(filePath);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "新增公益视频信息请求", httpMethod = "POST")
  @PostMapping("/create")
  public CommonResult<Boolean> create(@Param("视频信息") Video video) {
    try {
      Boolean save = videoService.create(video);
      return resultOk(save);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "编辑公益视频信息请求", httpMethod = "PUT")
  @PutMapping("/update")
  public CommonResult<Boolean> update(@Param("视频信息") Video video) {
    try {
      Boolean save = videoService.update(video);
      return resultOk(save);
    } catch (Exception e) {
      return resultError();
    }
  }
}
