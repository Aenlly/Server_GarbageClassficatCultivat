package com.aenlly.rcc.user.controller;

import com.aenlly.rcc.entity.HotInfoUserView;
import com.aenlly.rcc.user.service.IHotInfoUserViewService;
import com.aenlly.rcc.utils.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static com.aenlly.rcc.utils.ResultUtil.resultError;
import static com.aenlly.rcc.utils.ResultUtil.resultOk;

/**
 * VIEW 前端控制器
 *
 * @author aenlly
 * @since 2021-12-12
 */
@RestController
@Api(tags = "热门资讯管理控制器")
@RequestMapping("/hot-info-user-view")
public class HotInfoUserViewController {

  @Resource private IHotInfoUserViewService hotInfoUserViewService;

  @ApiOperation(value = "用户服务-热门资讯-请求", httpMethod = "GET")
  @GetMapping("/get")
  public CommonResult<List<HotInfoUserView>> getHotInfoUser() {
    try {
      List<HotInfoUserView> list = hotInfoUserViewService.list();
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "用户服务-热门资讯-搜索请求", httpMethod = "GET")
  @GetMapping("/getByTitle")
  public CommonResult<List<HotInfoUserView>> getHotInfoUserByTitle(
      @Param(value = "资讯标题") String title) {
    try {
      List<HotInfoUserView> list = hotInfoUserViewService.getHotInfoUserByTitleList(title);
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "用户服务-热门资讯-信息详情请求", httpMethod = "GET")
  @GetMapping("/getById")
  public CommonResult<HotInfoUserView> getHotInfoUserById(
      @Param(value = "资讯编号") Integer hotInfoId) {
    try {
      HotInfoUserView data = hotInfoUserViewService.getById(hotInfoId);
      return resultOk(data);
    } catch (Exception e) {
      return resultError();
    }
  }
}
