package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.CarouselUserView;
import com.aenlly.rcc.service.ICarouselUserViewService;
import com.aenlly.rcc.utils.CommonResult;
import com.aenlly.rcc.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * VIEW 前端控制器
 *
 * @author aenlly
 * @since 2021-12-11
 */
@RestController
@RequestMapping("/carousel-user-view")
@Api(tags = "轮播图视图管理")
public class CarouselUserViewController {

  @Resource ICarouselUserViewService carouselUserService;

  @ApiOperation(value = "首页轮播图列表请求", httpMethod = "GET")
  @GetMapping(value = "/get")
  public CommonResult<List<CarouselUserView>> getCarouselUserList() {
    try {
      List<CarouselUserView> list = carouselUserService.list();
      return ResultUtil.resultOk(list);
    } catch (Exception e) {
      return ResultUtil.resultError();
    }
  }
}
