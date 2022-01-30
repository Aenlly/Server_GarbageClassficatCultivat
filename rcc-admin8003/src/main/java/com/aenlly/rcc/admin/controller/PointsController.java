package com.aenlly.rcc.admin.controller;

import com.aenlly.rcc.admin.service.IPointsService;
import com.aenlly.rcc.entity.Points;
import com.aenlly.rcc.utils.CommonResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.aenlly.rcc.utils.ResultUtil.resultError;
import static com.aenlly.rcc.utils.ResultUtil.resultOk;

/**
 * 积分头衔 前端控制器
 *
 * @author aenlly
 * @since 2022-01-30
 */
@RestController
@Api(tags = "积分信息管理-积分头衔信息控制器")
@RequestMapping("/points")
public class PointsController {

  @Resource private IPointsService service;

  @ApiOperation(value = "请求积分头衔信息数据", httpMethod = "GET")
  @GetMapping("/getList")
  public CommonResult<IPage<Points>> getList(
      @Param("当前页码") @RequestParam("current") Integer current,
      @Param("每页数量") @RequestParam("size") Integer size,
      @Param("查询内容") @RequestParam("text") String text) {
    try {
      IPage<Points> list = service.getList(new Page<>(current, size), text);
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "根据id执行删除请求", httpMethod = "DELETE")
  @DeleteMapping("/delById")
  public CommonResult<Boolean> delById(@Param("主键") @RequestParam("id") Long id) {
    try {
      boolean b = service.removeById(id);
      if (b) {
        return resultOk(b);
      } else {
        throw new NullPointerException();
      }
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "根据id集合执行批量删除请求", httpMethod = "DELETE")
  @DeleteMapping("/delByIds")
  public CommonResult<Boolean> delByIds(@Param("主键集合") @RequestBody List<Long> ids) {
    try {
      boolean b = service.removeByIds(ids);
      if (b) {
        return resultOk(true);
      } else {
        throw new NullPointerException();
      }
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "新增积分头衔信息请求", httpMethod = "POST")
  @PostMapping("/create")
  public CommonResult<Boolean> create(@Param("积分头衔信息") Points entity) {
    try {
      Boolean save = service.save(entity);
      return resultOk(save);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "更新积分头衔信息请求", httpMethod = "PUT")
  @PutMapping("/update")
  public CommonResult<Boolean> update(@Param("积分头衔信息") Points entity) {
    try {
      Boolean save = service.updateById(entity);
      return resultOk(save);
    } catch (Exception e) {
      return resultError();
    }
  }
}
