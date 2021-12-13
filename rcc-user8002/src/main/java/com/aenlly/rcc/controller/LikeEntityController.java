package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.LikeEntity;
import com.aenlly.rcc.service.ILikeEntityService;
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
 * @since 2021-12-12
 */
@RestController
@RequestMapping("/like-entity")
@Api(tags = "点赞管理")
public class LikeEntityController {

  @Resource ILikeEntityService likeEntityService;

  @ApiOperation(value = "请求数据点赞量", httpMethod = "GET")
  @GetMapping("/getCountByDataId")
  public CommonResult<Long> getCountByDataId(
      @Param(value = "点赞的实体名称") String entityName, @Param("点赞数据ID") String dataId) {
    long count = likeEntityService.getCountByDataId(entityName, dataId);
    return resultOkOne(count);
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

  @ApiOperation(value = "请求判断当前用户是否点赞", httpMethod = "GET")
  @GetMapping("/getIsByUserId")
  public CommonResult<Boolean> getIsByUserId(
      @Param(value = "用户唯一标识") String userId,
      @Param(value = "收藏的实体") String entityName,
      @Param("点赞数据ID") String dataId) {
    Boolean isLike = likeEntityService.getIsByUserId(userId, entityName, dataId);
    return resultOkOne(isLike);
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

  @ApiOperation(value = "请求点赞创建", httpMethod = "POST")
  @PostMapping("/createLike")
  public CommonResult<Boolean> createLike(@Param("点赞接收实体") LikeEntity likeEntity) {
    // 判断是否存在点赞实体
    Boolean isLike =
        likeEntityService.getIsByUserId(
            likeEntity.getUserId(), likeEntity.getEntityName(), likeEntity.getDataId());
    if (!isLike) {
      // 创建信息
      boolean save = likeEntityService.save(likeEntity);
      if (save) {
        return resultOkOne(true);
      }
    }
    return resultErrorOne();
  }

  /**
   * 操作失败执行方法
   *
   * @return 返回内容
   */
  private CommonResult<Boolean> resultErrorOne() {
    return new CommonResult<>(CodeResult.ERROR, MessageResult.ERROR, null);
  }

  @ApiOperation(value = "取消点赞请求", httpMethod = "DELETE")
  @DeleteMapping("/likeCancel/{userId}/{entityName}/{dataId}")
  public CommonResult<Boolean> likeCancel(
      @Param(value = "用户唯一标识") @PathVariable("userId") String userId,
      @Param(value = "点赞的实体") @PathVariable("entityName") String entityName,
      @Param("点赞数据ID") @PathVariable("dataId") String dataId) {
    // 查询id进行删除
    Integer id = likeEntityService.getIdBy(userId, entityName, dataId);
    if (id != null) {
      boolean remove = likeEntityService.removeById(id);
      if (remove) {
        return resultOkOne(true);
      }
    }
    return resultErrorOne();
  }
}
