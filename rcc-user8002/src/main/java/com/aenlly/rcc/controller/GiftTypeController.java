package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.GiftType;
import com.aenlly.rcc.service.IGiftTypeService;
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
 * 前端控制器
 *
 * @author aenlly
 * @since 2021-12-15
 */
@RestController
@Api(tags = "礼品类型控制器")
@RequestMapping("/gift-type")
public class GiftTypeController {

  @Resource private IGiftTypeService giftTypeService;

  @ApiOperation(value = "用户礼品兑换展示的类型")
  @GetMapping("/getUserGiftTypeList")
  public CommonResult<List<GiftType>> getUserGiftTypeList() {
    try {
      List<GiftType> list = giftTypeService.getUserGiftTypeList();
      return resultOk(list);
    } catch (Exception e) {
      return resultErrorList();
    }
  }

  /**
   * 操作成功统一返回单个内容构造操作
   *
   * @param entity 单一实体内容
   * @return 返回内容
   */
  private CommonResult<GiftType> resultOk(GiftType entity) {
    return new CommonResult<>(CodeResult.OK, MessageResult.OK, entity);
  }

  /**
   * 操作成功统一返回列表内容构造操作
   *
   * @param list 列表实体内容
   * @return 返回内容
   */
  private CommonResult<List<GiftType>> resultOk(List<GiftType> list) {
    return new CommonResult<>(CodeResult.OK, MessageResult.OK, list);
  }

  /**
   * 操作失败执行方法
   *
   * @return 返回内容
   */
  private CommonResult<List<GiftType>> resultErrorList() {
    return new CommonResult<>(CodeResult.ERROR, MessageResult.ERROR, null);
  }

  /**
   * 操作失败执行方法
   *
   * @return 返回内容
   */
  private CommonResult<GiftType> resultErrorOne() {
    return new CommonResult<>(CodeResult.ERROR, MessageResult.ERROR, null);
  }
}
