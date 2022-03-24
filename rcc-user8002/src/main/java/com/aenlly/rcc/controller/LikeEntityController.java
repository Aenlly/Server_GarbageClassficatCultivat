package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.LikeEntity;
import com.aenlly.rcc.service.ILikeEntityService;
import com.aenlly.rcc.utils.CommonResult;
import com.aenlly.rcc.utils.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

  @Resource private ILikeEntityService likeEntityService;

  @ApiOperation(value = "请求数据点赞量")
  @GetMapping("/getCountByDataId")
  public CommonResult<Long> getCountByDataId(
          @ApiParam(value = "点赞的实体名称") String entityName, @ApiParam("点赞数据ID") String dataId) {
    try {
      long count = likeEntityService.getCountByDataId(entityName, dataId);
      return resultOk(count);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "请求判断当前用户是否点赞")
  @GetMapping("/getIsByUserId")
  public CommonResult<Boolean> getIsByUserId(
      @ApiParam("token") @RequestHeader("token") String token,
      @ApiParam(value = "收藏的实体") String entityName,
      @ApiParam("点赞数据ID") String dataId) {
    try {
      String userId = TokenUtil.toUserId(token);
      Boolean isLike = likeEntityService.getIsByUserId(userId, entityName, dataId);
      return resultOk(isLike);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "请求点赞创建")
  @PostMapping("/createLike")
  public CommonResult<Boolean> createLike(
      @ApiParam("token") @RequestHeader("token") String token,
      @ApiParam("点赞接收实体") LikeEntity likeEntity) {
    try {
      String userId = TokenUtil.toUserId(token);
      // 判断是否存在点赞实体
      Boolean isLike =
          likeEntityService.getIsByUserId(
              userId, likeEntity.getEntityName(), likeEntity.getDataId());
      if (!isLike) {
        likeEntity.setUserId(userId);
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

  @ApiOperation(value = "取消点赞请求")
  @DeleteMapping("/likeCancel/{entityName}/{dataId}")
  public CommonResult<Boolean> likeCancel(
      @ApiParam("token") @RequestHeader("token") String token,
      @ApiParam(value = "点赞的实体") @PathVariable("entityName") String entityName,
      @ApiParam("点赞数据ID") @PathVariable("dataId") String dataId) {
    try {
      String userId = TokenUtil.toUserId(token);
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
