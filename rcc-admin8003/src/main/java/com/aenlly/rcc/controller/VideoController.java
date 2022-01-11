package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.Video;
import com.aenlly.rcc.enums.VideoCheckEnum;
import com.aenlly.rcc.service.IVideoService;
import com.aenlly.rcc.utils.CommonResult;
import com.aenlly.rcc.utils.enums.QueryVideoType;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

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
@CrossOrigin // 允许跨域请求
public class VideoController {
  /** 视频信息表服务类 */
  @Resource private IVideoService videoService;

  @ApiOperation(value = "请求视频信息数据据", httpMethod = "GET")
  @GetMapping("/getVideoList")
  public CommonResult<IPage<Video>> getVideoList(
      @Param("当前页码") @RequestParam("current") Integer current,
      @Param("每页数量") @RequestParam("size") Integer size,
      @Param("查询类型") @RequestParam("queryType") QueryVideoType queryType,
      @Param("查询内容") @RequestParam("text") String text) {
    try {
      IPage<Video> list = videoService.getVideoList(new Page<>(current, size), queryType, text);
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "根据id执行删除请求", httpMethod = "DELETE")
  @DeleteMapping("/delVideoById")
  public CommonResult<Boolean> delVideoById(@Param("主键") @RequestParam("id") Long id) {
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
  @DeleteMapping("/delVideoByIds")
  public CommonResult<Boolean> delVideoByIds(@Param("主键集合") @RequestBody List<Long> ids) {
    try {
      boolean b = videoService.removeByIds(ids);
      if (b) {
        return resultOk(b);
      } else {
        throw new NullPointerException();
      }
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "根据id发布数据请求", httpMethod = "PUT")
  @PutMapping("/putPublish/{id}")
  public CommonResult<Boolean> putPublish(@Param("主键") @PathVariable("id") Long id) {
    try {
      Boolean check = videoService.putVideoByIdCheck(id, VideoCheckEnum.PUBLISH_OK);
      return resultOk(check);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "根据id下线数据请求", httpMethod = "PUT")
  @PutMapping("/putShelf/{id}")
  public CommonResult<Boolean> putShelf(@Param("主键") @PathVariable("id") Long id) {
    try {
      Boolean check = videoService.putVideoByIdCheck(id, VideoCheckEnum.PUBLISH_NOT);
      return resultOk(check);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "根据id置顶数据请求", httpMethod = "PUT")
  @PutMapping("/putTop/{id}")
  public CommonResult<Boolean> putTop(@Param("主键") @PathVariable("id") Long id) {
    try {
      Boolean check = videoService.putVideoByIdCheck(id, VideoCheckEnum.TOP);
      return resultOk(check);
    } catch (Exception e) {
      return resultError();
    }
  }
}
