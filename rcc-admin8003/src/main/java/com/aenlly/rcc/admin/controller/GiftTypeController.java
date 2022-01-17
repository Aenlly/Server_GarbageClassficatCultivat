package com.aenlly.rcc.admin.controller;

import com.aenlly.rcc.admin.service.IGiftTypeService;
import com.aenlly.rcc.entity.GiftType;
import com.aenlly.rcc.utils.CommonResult;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static com.aenlly.rcc.utils.ResultUtil.resultError;
import static com.aenlly.rcc.utils.ResultUtil.resultOk;

/**
 * 礼品类型表 前端控制器
 *
 * @author aenlly
 * @since 2022-01-17
 */
@RestController
@RequestMapping("/gift-type")
public class GiftTypeController {

  @Resource private IGiftTypeService service;

  @ApiOperation(value = "根据类型名称请求列表数据", httpMethod = "GET")
  @GetMapping("/getSelectListBy")
  public CommonResult<List<GiftType>> getSelectListBy(
      @Param("类型名称") @RequestParam("text") String text) {
    try {
      List<GiftType> list = service.getSelectListBy(text);
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }
}
