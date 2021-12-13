package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.VideoUserView;
import com.aenlly.rcc.service.IVideoUserViewService;
import com.aenlly.rcc.utils.CodeResult;
import com.aenlly.rcc.utils.CommonResult;
import com.aenlly.rcc.utils.MessageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * VIEW 前端控制器
 *
 * @author aenlly
 * @since 2021-12-13
 */
@RestController
@Api(tags = "用户服务-视频模块管理")
@RequestMapping("/video-user-view")
public class VideoUserViewController {

  @Resource IVideoUserViewService videoUserViewService;

  @ApiOperation(value = "查询置顶视频信息", httpMethod = "GET")
  @GetMapping("/getByChekTop")
  public CommonResult<VideoUserView> getByChekTop() {
    VideoUserView video = videoUserViewService.getByChekTop();
    return resultOkOne(video);
  }

  @ApiOperation(value = "用户公益视频展示", httpMethod = "GET")
  @GetMapping("/get")
  public CommonResult<List<VideoUserView>> getListByCheck() {
    List<VideoUserView> list = videoUserViewService.list();
    return resultOkList(list);
  }

  @ApiOperation(value = "请求用户服务-公益视频搜索查询", httpMethod = "GET")
  @GetMapping("/getByTitle")
  public CommonResult<List<VideoUserView>> getHotInfoUserByTitle(
      @Param(value = "公益视频标题") String title) {
    List<VideoUserView> list = videoUserViewService.getByTitleList(title);
    return resultOkList(list);
  }

  @ApiOperation(value = "请求用户服务-公益视频信息详情", httpMethod = "GET")
  @GetMapping("/getById")
  public CommonResult<VideoUserView> getInfoUserById(@Param(value = "视频编号") Long id) {
    VideoUserView data = videoUserViewService.getById(id);
    return resultOkOne(data);
  }

  @ApiOperation(value = "用户通过分享的链接进入时，分享数量增加", httpMethod = "PUT")
  @PutMapping("/upShareCount/{id}")
  public CommonResult<Long> upShareCount(@Param(value = "视频编号") @PathVariable("id") Long id) {
    Boolean flag = videoUserViewService.upShareCount(id);
    if (flag) {
      VideoUserView byId = videoUserViewService.getById(id);
      return resultOk(byId.getShareCount());
    }
    return resultError();
  }

  /**
   * 操作成功统一返回单个内容构造操作
   *
   * @param list 列表实体内容
   * @return 返回内容
   */
  private CommonResult<List<VideoUserView>> resultOkList(List<VideoUserView> list) {
    return new CommonResult<>(CodeResult.OK, MessageResult.OK, list);
  }

  /**
   * 操作成功统一返回单个内容构造操作
   *
   * @param video 单一实体内容
   * @return 返回内容
   */
  private CommonResult<VideoUserView> resultOkOne(VideoUserView video) {
    return new CommonResult<>(CodeResult.OK, MessageResult.OK, video);
  }

  /**
   * 成功
   *
   * @return 返回内容
   */
  private CommonResult<Long> resultOk(Long count) {
    return new CommonResult<>(CodeResult.OK, MessageResult.OK, count);
  }

  /**
   * 失败
   *
   * @return 返回内容
   */
  private CommonResult<Long> resultError() {
    return new CommonResult<>(CodeResult.ERROR, MessageResult.ERROR, null);
  }
}
