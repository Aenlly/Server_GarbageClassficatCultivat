package com.aenlly.rcc.user.controller;

import com.aenlly.rcc.entity.GiftListView;
import com.aenlly.rcc.user.service.IGiftListViewService;
import com.aenlly.rcc.utils.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.aenlly.rcc.utils.ResultUtil.resultError;
import static com.aenlly.rcc.utils.ResultUtil.resultOk;

/**
 * VIEW 前端控制器
 *
 * @author aenlly
 * @since 2021-12-15
 */
@RestController
@Api(tags = "礼品信息视图管理控制器")
@RequestMapping("/gift-list-view")
public class GiftListViewController {

  @Resource private IGiftListViewService giftListViewService;

  @ApiOperation(value = "用户积分兑换-信息请求，参数用于条件搜索", httpMethod = "GET")
  @GetMapping("/getUserGiftView")
  public CommonResult<List<GiftListView>> getUserGiftView(
      @Param("礼品名称") @RequestParam("name") String name,
      @Param("礼品类型") @RequestParam("type") Integer type) {
    try {
      List<GiftListView> list = giftListViewService.getUserGiftList(name, type);
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "根据id请求礼品数据", httpMethod = "GET")
  @GetMapping("/getById/{id}")
  public CommonResult<GiftListView> getById(@Param("礼品编号") @PathVariable("id") Long id) {
    try {
      GiftListView view = giftListViewService.getById(id);
      return resultOk(view);
    } catch (Exception e) {
      return resultError();
    }
  }
}
