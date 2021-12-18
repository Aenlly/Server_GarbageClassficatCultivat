package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.WasteTurnTreasure;
import com.aenlly.rcc.service.IWasteTurnTreasureService;
import com.aenlly.rcc.utils.CommonResult;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static com.aenlly.rcc.utils.ResultUtil.resultError;
import static com.aenlly.rcc.utils.ResultUtil.resultOk;

/**
 * 变废为宝表 前端控制器
 *
 * @author aenlly
 * @since 2021-12-18
 */
@RestController
@RequestMapping("/waste-turn-treasure")
public class WasteTurnTreasureController {

  /** 变废为宝表-服务对象 */
  @Resource IWasteTurnTreasureService wasteTurnTreasureService;

  @ApiOperation(value = "根据标签获取变废为宝通过的信息列表", httpMethod = "GET")
  @GetMapping("/getListByTag/{tag}")
  @JsonCreator
  public CommonResult<List<WasteTurnTreasure>> getListByTag(
      @Param("标签") @PathVariable("tag") Integer tag) {
    try {
      List<WasteTurnTreasure> list = wasteTurnTreasureService.getListByTag(tag);
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }
}
