package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.HotInfoUserView;
import com.aenlly.rcc.service.IHotInfoUserViewService;
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
 * VIEW 前端控制器
 *
 * @author aenlly
 * @since 2021-12-12
 */
@RestController
@RequestMapping("/hot-info-user-view")
@Api(tags = "热门资讯控制")
public class HotInfoUserViewController {

  @Resource IHotInfoUserViewService hotInfoUserViewService;

  @ApiOperation(value = "请求用户服务热门资讯", httpMethod = "GET")
  @GetMapping("/get")
  public CommonResult<List<HotInfoUserView>> getHotInfoUser() {
    List<HotInfoUserView> list = hotInfoUserViewService.list();
    return resultOkList(list);
  }

  /**
   * 操作成功统一返回列表内容构造操作执行方法
   *
   * @param list 列表内容
   * @return 返回内容
   */
  private CommonResult<List<HotInfoUserView>> resultOkList(List<HotInfoUserView> list) {
    return new CommonResult<>(CodeResult.OK, MessageResult.OK, list);
  }

  @ApiOperation(value = "请求用户服务热门资讯搜索查询", httpMethod = "GET")
  @GetMapping("/getByTitle")
  public CommonResult<List<HotInfoUserView>> getHotInfoUserByTitle(
      @Param(value = "资讯标题") String title) {
    List<HotInfoUserView> list = hotInfoUserViewService.getHotInfoUserByTitleList(title);
    return resultOkList(list);
  }

  @ApiOperation(value = "请求用户服务热门资讯单个信息详情", httpMethod = "GET")
  @GetMapping("/getById")
  public CommonResult<HotInfoUserView> getHotInfoUserById(
      @Param(value = "资讯标题") Integer hotInfoId) {
    HotInfoUserView data = hotInfoUserViewService.getById(hotInfoId);
    return resultOkOne(data);
  }

  /**
   * 操作成功统一返回单个内容构造操作
   *
   * @param hotInfoUserView 单一实体内容
   * @return 返回内容
   */
  private CommonResult<HotInfoUserView> resultOkOne(HotInfoUserView hotInfoUserView) {
    return new CommonResult<>(CodeResult.OK, MessageResult.OK, hotInfoUserView);
  }

  /**
   * 操作失败执行方法
   *
   * @return 返回内容
   */
  private CommonResult<List<HotInfoUserView>> resultErrorList() {
    return new CommonResult<>(CodeResult.ERROR, MessageResult.ERROR, null);
  }

  /**
   * 操作失败执行方法
   *
   * @return 返回内容
   */
  private CommonResult<HotInfoUserView> resultErrorOne() {
    return new CommonResult<>(CodeResult.ERROR, MessageResult.ERROR, null);
  }
}
