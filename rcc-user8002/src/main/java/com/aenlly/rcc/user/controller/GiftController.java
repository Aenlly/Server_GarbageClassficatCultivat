package com.aenlly.rcc.user.controller;

import com.aenlly.rcc.user.service.IGiftService;
import com.aenlly.rcc.utils.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.aenlly.rcc.utils.ResultUtil.*;

/**
 * 礼品信息表 前端控制器
 *
 * @author aenlly
 * @since 2021-12-25
 */
@RestController
@Api(tags = "礼品信息管理控制器")
@RequestMapping("/gift")
public class GiftController {

  /** 礼品兑换服务对象 */
  @Resource private IGiftService giftService;

  @ApiOperation(value = "兑换礼品请求", httpMethod = "PUT")
  @PutMapping("/convertById/{userId}/{id}")
  public CommonResult<String> convertById(
      @Param("用户编号") @PathVariable("userId") String userId,
      @Param("礼品编号") @PathVariable("id") Long id) {
    try {
      String code_key = giftService.convertById(userId, id);
      return resultOk(code_key);
    } catch (IndexOutOfBoundsException e) {
      return resultNOT_POINT();
    } catch (Exception e) {
      return resultError();
    }
  }
}
