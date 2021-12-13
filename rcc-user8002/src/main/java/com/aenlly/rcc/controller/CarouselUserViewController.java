package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.CarouselUserView;
import com.aenlly.rcc.service.ICarouselUserViewService;
import com.aenlly.rcc.utils.CodeResult;
import com.aenlly.rcc.utils.CommonResult;
import com.aenlly.rcc.utils.MessageResult;
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
    List<CarouselUserView> list = carouselUserService.list();
    return resultOkList(list);
  }

  /**
   * 操作成功统一返回列表内容构造操作执行方法
   *
   * @param list 列表内容
   * @return 返回内容
   */
  public CommonResult<List<CarouselUserView>> resultOkList(List<CarouselUserView> list) {
    return new CommonResult<>(CodeResult.OK, MessageResult.OK, list);
  }
}
