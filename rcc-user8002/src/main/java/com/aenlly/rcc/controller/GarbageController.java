package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.Garbage;
import com.aenlly.rcc.entity.GarbageList;
import com.aenlly.rcc.service.IGarbageListService;
import com.aenlly.rcc.service.IGarbageService;
import com.aenlly.rcc.utils.CodeResult;
import com.aenlly.rcc.utils.CommonResult;
import com.aenlly.rcc.utils.MessageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
    Garbage garbage = garbageService.getByType(garbageType);
    List<GarbageList> list = garbageListService.getByGarbageId(garbage.getGarbageId());
    garbage.setGarbageLists(list);
    System.out.println(garbage);
    return resultOkOne(garbage);
  }

  /**
   * 操作成功统一返回单个内容构造操作
   *
   * @param garbage 单一实体内容
   * @return 返回内容
   */
  private CommonResult<Garbage> resultOkOne(Garbage garbage) {
    return new CommonResult<>(CodeResult.OK, MessageResult.OK, garbage);
  }

  /**
   * 操作成功统一返回列表内容构造操作执行方法
   *
   * @param list 列表内容
   * @return 返回内容
   */
  private CommonResult<List<Garbage>> resultOkList(List<Garbage> list) {
    return new CommonResult<>(CodeResult.OK, MessageResult.OK, list);
  }

  /**
   * 操作失败执行方法
   *
   * @return 返回内容
   */
  private CommonResult<List<Garbage>> resultErrorList() {
    return new CommonResult<>(CodeResult.ERROR, MessageResult.ERROR, null);
  }

  /**
   * 操作失败执行方法
   *
   * @return 返回内容
   */
  private CommonResult<Garbage> resultErrorOne() {
    return new CommonResult<>(CodeResult.ERROR, MessageResult.ERROR, null);
  }
}
