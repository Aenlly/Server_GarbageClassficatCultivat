package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.CollectEntity;
import com.aenlly.rcc.service.ICollectEntityService;
import com.aenlly.rcc.utils.CodeResult;
import com.aenlly.rcc.utils.CommonResult;
import com.aenlly.rcc.utils.MessageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 前端控制器
 *
 * @author aenlly
 * @since 2021-12-13
 */
@RestController
@RequestMapping("/collect-entity")
@Api(tags = "收藏管理")
public class CollectEntityController {

  @Resource ICollectEntityService collectEntityService;

  @ApiOperation(value = "请求数据点赞量", httpMethod = "GET")
  @GetMapping("/getCountByDataId")
  public CommonResult<Long> getCountByDataId(
      @Param(value = "收藏的实体名称") String entityName, @Param("收藏的数据ID") String dataId) {
    long count = collectEntityService.getCountByDataId(entityName, dataId);
    return resultOkOne(count);
  }

  @ApiOperation(value = "请求判断当前用户是否收藏", httpMethod = "GET")
  @GetMapping("/getIsByUserId")
  public CommonResult<Boolean> getIsByUserId(
      @Param(value = "用户唯一标识") String userId,
      @Param(value = "收藏的实体") String entityName,
      @Param("点赞数据ID") String dataId) {
    Boolean isCollect = collectEntityService.getIsByUserId(userId, entityName, dataId);
    return resultOkOne(isCollect);
  }

  /**
   * 操作成功统一返回单个内容构造操作
   *
   * @param isLike 是否存在
   * @return 返回内容
   */
  private CommonResult<Boolean> resultOkOne(boolean isLike) {
    return new CommonResult<>(CodeResult.OK, MessageResult.OK, isLike);
  }

  @ApiOperation(value = "请求收藏创建", httpMethod = "POST")
  @PostMapping("/createCollect")
  public CommonResult<Boolean> createLike(@Param("收藏接收实体") CollectEntity collectEntity) {

    // 判断是否存在点赞实体
    Boolean isCollect =
        collectEntityService.getIsByUserId(
            collectEntity.getUserId(), collectEntity.getEntityName(), collectEntity.getDataId());
    if (!isCollect) {
      // 创建信息
      boolean save = collectEntityService.save(collectEntity);
      if (save) {
        return resultOkOne(true);
      }
    }
    return resultErrorOne();
  }

  @ApiOperation(value = "取消收藏请求", httpMethod = "DELETE")
  @DeleteMapping("/collectCancel/{userId}/{entityName}/{dataId}")
  public CommonResult<Boolean> collectCancel(
      @Param(value = "用户唯一标识") @PathVariable("userId") String userId,
      @Param(value = "收藏的实体") @PathVariable("entityName") String entityName,
      @Param("收藏数据ID") @PathVariable("dataId") String dataId) {
    // 查询id进行删除
    Integer id = collectEntityService.getIdBy(userId, entityName, dataId);
    if (id != null) {
      boolean remove = collectEntityService.removeById(id);
      if (remove) {
        return resultOkOne(true);
      }
    }
    return resultErrorOne();
  }

  /**
   * 操作成功统一返回单个内容构造操作
   *
   * @param count 统计数量
   * @return 返回内容
   */
  private CommonResult<Long> resultOkOne(Long count) {
    return new CommonResult<>(CodeResult.OK, MessageResult.OK, count);
  }

  /**
   * 操作失败执行方法
   *
   * @return 返回内容
   */
  private CommonResult<Boolean> resultErrorOne() {
    return new CommonResult<>(CodeResult.ERROR, MessageResult.ERROR, null);
  }
}