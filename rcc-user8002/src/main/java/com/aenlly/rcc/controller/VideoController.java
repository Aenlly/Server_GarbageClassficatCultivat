package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.Video;
import com.aenlly.rcc.service.IVideoService;
import com.aenlly.rcc.utils.CodeResult;
import com.aenlly.rcc.utils.CommonResult;
import com.aenlly.rcc.utils.MessageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 前端控制器
 *
 * @author aenlly
 * @since 2021-12-11
 */
@RestController
@Api(tags = "视频管理")
@RequestMapping("/video")
public class VideoController {

  @Resource IVideoService videoService;

  @ApiOperation(value = "查询置顶视频信息", httpMethod = "GET")
  @GetMapping("/getByChekTop")
  public CommonResult<Video> getByChekTop() {
    Video video = videoService.getByChekTop();
    return resultOkOne(video);
  }

  /**
   * 操作成功统一返回单个内容构造操作
   *
   * @param video 单一实体内容
   * @return 返回内容
   */
  private CommonResult<Video> resultOkOne(Video video) {
    return new CommonResult<>(CodeResult.OK, MessageResult.OK, video);
  }

  @ApiOperation(value = "创建视频信息")
  @PostMapping(value = "/create")
  public Integer create(@Param(value = "视频参数接收实体") Video video) {
    return CodeResult.OK.getCode();
  }

  /**
   * 是否成功
   *
   * @return
   */
  private CommonResult<Video> resultOk() {
    return new CommonResult<>(CodeResult.OK, MessageResult.OK, null);
  }
}
