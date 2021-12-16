package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.Garbage;
import com.aenlly.rcc.entity.GarbageList;
import com.aenlly.rcc.service.IGarbageListService;
import com.aenlly.rcc.service.IGarbageService;
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
 * 前端控制器
 *
 * @author aenlly
 * @since 2021-12-12
 */
@RestController
@RequestMapping("/garbage")
@Api(tags = "垃圾管理")
public class GarbageController {

  @Resource IGarbageService garbageService;
  @Resource IGarbageListService garbageListService;

  @ApiOperation(value = "小程序垃圾类型详情请求", httpMethod = "GET")
  @GetMapping("/getByType")
  public CommonResult<Garbage> getByType(@Param(value = "垃圾类型") String garbageType) {
    try {
      Garbage garbage = garbageService.getByType(garbageType);
      List<GarbageList> list = garbageListService.getByGarbageId(garbage.getGarbageId());
      garbage.setGarbageLists(list);
      return resultOk(garbage);
    } catch (Exception e) {
      return resultError();
    }
  }
}
