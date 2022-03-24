package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.GiftType;
import com.aenlly.rcc.service.IGiftTypeService;
import com.aenlly.rcc.utils.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static com.aenlly.rcc.utils.ResultUtil.resultError;
import static com.aenlly.rcc.utils.ResultUtil.resultOk;

/**
 * 前端控制器
 *
 * @author aenlly
 * @since 2021-12-15
 */
@RestController
@Api(tags = "礼品类型管理控制器")
@RequestMapping("/gift-type")
public class GiftTypeController {

  @Resource private IGiftTypeService giftTypeService;

  @ApiOperation(value = "积分兑换-礼品类型-信息数据请求")
  @GetMapping("/getUserGiftTypeList")
  public CommonResult<List<GiftType>> getUserGiftTypeList() {
    try {
      List<GiftType> list = giftTypeService.getUserGiftTypeList();
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }
}
