package com.aenlly.rcc.controller;

import com.aenlly.rcc.service.IGiftService;
import com.aenlly.rcc.utils.CommonResult;
import com.aenlly.rcc.utils.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

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

  @ApiOperation(value = "兑换礼品请求")
  @PutMapping("/convertById/{id}")
  public CommonResult<String> convertById(
      @ApiParam("token") @RequestHeader("token") String token,
      @ApiParam("礼品编号") @PathVariable("id") Long id) {
    try {
      String userId = TokenUtil.toUserId(token);
      String code_key = giftService.convertById(userId, id);
      return resultOk(code_key);
    } catch (IndexOutOfBoundsException e) {
      return resultNOT_POINT();
    } catch (Exception e) {
      return resultError();
    }
  }
}
