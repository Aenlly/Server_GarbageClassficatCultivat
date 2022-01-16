package com.aenlly.rcc.user.controller;

import com.aenlly.rcc.entity.LikeEntity;
import com.aenlly.rcc.user.service.ILikeEntityService;
import com.aenlly.rcc.utils.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.aenlly.rcc.utils.ResultUtil.resultError;
import static com.aenlly.rcc.utils.ResultUtil.resultOk;

/**
 * 前端控制器
 *
 * @author aenlly
 * @since 2021-12-12
 */
@RestController
@Api(tags = "点赞管理控制器")
@RequestMapping("/like-entity")
public class LikeEntityController {

  @Resource ILikeEntityService likeEntityService;

  @ApiOperation(value = "请求数据点赞量", httpMethod = "GET")
  @GetMapping("/getCountByDataId")
  public CommonResult<Long> getCountByDataId(
      @Param(value = "点赞的实体名称") String entityName, @Param("点赞数据ID") String dataId) {
    try {
      long count = likeEntityService.getCountByDataId(entityName, dataId);
      return resultOk(count);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "请求判断当前用户是否点赞", httpMethod = "GET")
  @GetMapping("/getIsByUserId")
  public CommonResult<Boolean> getIsByUserId(
      @Param(value = "用户唯一标识") String userId,
      @Param(value = "收藏的实体") String entityName,
      @Param("点赞数据ID") String dataId) {
    try {
      Boolean isLike = likeEntityService.getIsByUserId(userId, entityName, dataId);
      return resultOk(isLike);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "请求点赞创建", httpMethod = "POST")
  @PostMapping("/createLike")
  public CommonResult<Boolean> createLike(@Param("点赞接收实体") LikeEntity likeEntity) {
    try {
      // 判断是否存在点赞实体
      Boolean isLike =
          likeEntityService.getIsByUserId(
              likeEntity.getUserId(), likeEntity.getEntityName(), likeEntity.getDataId());
      if (!isLike) {
        // 创建信息
        boolean save = likeEntityService.save(likeEntity);
        if (save) {
          return resultOk(true);
        }
      }
      return resultError();
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "取消点赞请求", httpMethod = "DELETE")
  @DeleteMapping("/likeCancel/{userId}/{entityName}/{dataId}")
  public CommonResult<Boolean> likeCancel(
      @Param(value = "用户唯一标识") @PathVariable("userId") String userId,
      @Param(value = "点赞的实体") @PathVariable("entityName") String entityName,
      @Param("点赞数据ID") @PathVariable("dataId") String dataId) {
    try {
      // 查询id进行删除
      Integer id = likeEntityService.getIdBy(userId, entityName, dataId);
      if (id != null) {
        boolean remove = likeEntityService.removeById(id);
        if (remove) {
          return resultOk(true);
        }
      }
      return resultError();
    } catch (Exception e) {
      return resultError();
    }
  }
}
