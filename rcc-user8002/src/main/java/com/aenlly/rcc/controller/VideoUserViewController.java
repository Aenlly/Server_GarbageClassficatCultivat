package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.VideoUserView;
import com.aenlly.rcc.service.IVideoUserViewService;
import com.aenlly.rcc.utils.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.aenlly.rcc.utils.ResultUtil.resultError;
import static com.aenlly.rcc.utils.ResultUtil.resultOk;

/**
 用户公益视频视图 VIEW 前端控制器

 @author aenlly
 @since 2021-12-13 */
@RestController
@Api(tags = "用户服务-视频模块管理控制器")
@RequestMapping("/video-user-view")
public class VideoUserViewController {

    @Resource
    private IVideoUserViewService videoUserViewService;

    @ApiOperation(value = "查询置顶视频信息")
    @GetMapping("/getByChekTop")
    public CommonResult<VideoUserView> getByChekTop() {
        try {
            VideoUserView video = videoUserViewService.getByChekTop();
            return resultOk(video);
        } catch (Exception e) {
            return resultError();
        }
    }

    @ApiOperation(value = "查询公益视频列表")
    @GetMapping("/get")
    public CommonResult<List<VideoUserView>> getListByCheck() {
        try {
            List<VideoUserView> list = videoUserViewService.getList();
            return resultOk(list);
        } catch (Exception e) {
            return resultError();
        }
    }

    @ApiOperation(value = "用户服务-公益视频搜索请求")
    @GetMapping("/getByTitle")
    public CommonResult<List<VideoUserView>> getHotInfoUserByTitle(@ApiParam(value = "公益视频标题") String title) {
        try {
            List<VideoUserView> list = videoUserViewService.getByTitleList(title);
            return resultOk(list);
        } catch (Exception e) {
            return resultError();
        }
    }

    @ApiOperation(value = "用户服务-公益视频信息详情请求")
    @GetMapping("/getById")
    public CommonResult<VideoUserView> getInfoUserById(@ApiParam(value = "视频编号") Long id) {
        try {
            VideoUserView data = videoUserViewService.getById(id);
            return resultOk(data);
        } catch (Exception e) {
            return resultError();
        }
    }

    @ApiOperation(value = "分享操作，用户通过分享的链接进入时，分享数量增加")
    @PutMapping("/upShareCount/{id}")
    public CommonResult<Long> upShareCount(@ApiParam(value = "视频编号") @PathVariable("id") Long id) {
        Boolean flag = videoUserViewService.upShareCount(id);
        if (flag) {
            VideoUserView byId = videoUserViewService.getById(id);
            return resultOk(byId.getShareCount());
        }
        return resultError();
    }
}
