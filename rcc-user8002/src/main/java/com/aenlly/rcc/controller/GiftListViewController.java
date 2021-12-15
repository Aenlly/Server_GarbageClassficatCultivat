package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.GiftListView;
import com.aenlly.rcc.service.IGiftListViewService;
import com.aenlly.rcc.utils.CodeResult;
import com.aenlly.rcc.utils.CommonResult;
import com.aenlly.rcc.utils.MessageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * VIEW 前端控制器
 *
 * @author aenlly
 * @since 2021-12-15
 */
@RestController
@Api(tags = "礼品信息视图控制器")
@RequestMapping("/gift-list-view")
public class GiftListViewController {

  @Resource IGiftListViewService giftListViewService;

  @ApiOperation(value = "用户礼品兑换显示视图，参数用于条件搜索")
  @GetMapping("/getUserGiftView")
  public CommonResult<List<GiftListView>> getUserGiftView(
      @Param("礼品名称") @RequestParam("name") String name,
      @Param("礼品类型") @RequestParam("type") Integer type) {
    try {
      List<GiftListView> list = giftListViewService.getUserGiftList(name, type);
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
  private CommonResult<GiftListView> resultOk(GiftListView entity) {
    return new CommonResult<>(CodeResult.OK, MessageResult.OK, entity);
  }

  /**
   * 操作成功统一返回列表内容构造操作
   *
   * @param list 列表实体内容
   * @return 返回内容
   */
  private CommonResult<List<GiftListView>> resultOk(List<GiftListView> list) {
    return new CommonResult<>(CodeResult.OK, MessageResult.OK, list);
  }

  /**
   * 操作失败执行方法
   *
   * @return 返回内容
   */
  private CommonResult<List<GiftListView>> resultErrorList() {
    return new CommonResult<>(CodeResult.ERROR, MessageResult.ERROR, null);
  }

  /**
   * 操作失败执行方法
   *
   * @return 返回内容
   */
  private CommonResult<GiftListView> resultErrorOne() {
    return new CommonResult<>(CodeResult.ERROR, MessageResult.ERROR, null);
  }
}
