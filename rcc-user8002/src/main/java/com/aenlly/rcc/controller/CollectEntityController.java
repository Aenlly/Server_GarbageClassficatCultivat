package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.CollectEntity;
import com.aenlly.rcc.service.ICollectEntityService;
import com.aenlly.rcc.utils.CommonResult;
import com.aenlly.rcc.utils.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.aenlly.rcc.utils.ResultUtil.resultError;
import static com.aenlly.rcc.utils.ResultUtil.resultOk;

/**
 * 前端控制器
 *
 * @author aenlly
 * @since 2021-12-13
 */
@RestController
@Api(tags = "用户服务-收藏管理控制器")
@RequestMapping("/collect-entity")
public class CollectEntityController {

  @Resource private ICollectEntityService collectEntityService;

  @ApiOperation(value = "用户收藏-列表请求")
  @GetMapping("/getByUserId")
  public CommonResult<List<CollectEntity>> getListByUserId(
      @ApiParam("token") @RequestHeader("token") String token,
      @ApiParam("搜索内容，用于搜索时使用") @RequestParam("name") String name) {
    try {
      String userId = TokenUtil.toUserId(token);
      List<CollectEntity> list = collectEntityService.getListByUserId(userId, name);
      return resultOk(list);
    } catch (Exception e) {
      return null;
    }
  }

  @ApiOperation(value = "用户服务-变废为宝-收藏-列表请求")
  @GetMapping("/getByUserIdAndEntityName")
  public CommonResult<List<CollectEntity>> getByUserIdAndEntityName(
      @ApiParam("token") @RequestHeader("token") String token,
      @ApiParam("搜索内容，用于搜索时使用") @RequestParam("name") String name,
      @ApiParam("搜索实体名称") @RequestParam("entityName") String entityName) {
    try {
      String userId = TokenUtil.toUserId(token);
      List<CollectEntity> list =
          collectEntityService.getByUserIdAndEntityName(userId, name, entityName);
      return resultOk(list);
    } catch (Exception e) {
      return null;
    }
  }

  @ApiOperation(value = "请求数据收藏量")
  @GetMapping("/getCountByDataId")
  public CommonResult<Long> getCountByDataId(
      @ApiParam(value = "收藏的实体名称") String entityName, @ApiParam("收藏的数据ID") String dataId) {
    long count = collectEntityService.getCountByDataId(entityName, dataId);
    return resultOk(count);
  }

  @ApiOperation(value = "请求判断当前用户是否收藏")
  @GetMapping("/getIsByUserId")
  public CommonResult<Boolean> getIsByUserId(
      @ApiParam("token") @RequestHeader("token") String token,
      @ApiParam(value = "收藏的实体") String entityName,
      @ApiParam("点赞数据ID") String dataId) {
    try {
      String userId = TokenUtil.toUserId(token);
      Boolean isCollect = collectEntityService.getIsByUserId(userId, entityName, dataId);
      return resultOk(isCollect);
    } catch (Exception e) {
      resultError();
    }
    return resultError();
  }

  @ApiOperation(value = "请求收藏创建")
  @PostMapping("/createCollect")
  public CommonResult<Boolean> createLike(
      @ApiParam("token") @RequestHeader("token") String token,
      @ApiParam("收藏接收实体") CollectEntity collectEntity) {
    try {
      String userId = TokenUtil.toUserId(token);
      // 判断是否存在点赞实体
      Boolean isCollect =
          collectEntityService.getIsByUserId(
              userId, collectEntity.getEntityName(), collectEntity.getDataId());
      if (!isCollect) {
        collectEntity.setUserId(userId);
        // 创建信息
        boolean save = collectEntityService.save(collectEntity);
        if (save) {
          return resultOk(true);
        }
      }
    } catch (Exception e) {
      return resultError();
    }
    return resultError();
  }

  @ApiOperation(value = "取消收藏请求")
  @DeleteMapping("/collectCancel/{entityName}/{dataId}")
  public CommonResult<Boolean> collectCancel(
      @ApiParam("token") @RequestHeader("token") String token,
      @ApiParam(value = "收藏的实体") @PathVariable("entityName") String entityName,
      @ApiParam("收藏数据ID") @PathVariable("dataId") String dataId) {
    try {
      String userId = TokenUtil.toUserId(token);
      // 查询id进行删除
      Integer id = collectEntityService.getIdBy(userId, entityName, dataId);
      if (id != null) {
        boolean remove = collectEntityService.removeById(id);
        if (remove) {
          return resultOk(true);
        }
      }
    } catch (Exception e) {
      return resultError();
    }
    return resultError();
  }
}
