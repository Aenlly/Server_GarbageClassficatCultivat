package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.OptionsTable;
import com.aenlly.rcc.service.IOptionsTableService;
import com.aenlly.rcc.utils.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.aenlly.rcc.utils.ResultUtil.resultError;
import static com.aenlly.rcc.utils.ResultUtil.resultOk;

/**
 * 选项表 前端控制器
 *
 * @author aenlly
 * @since 2022-02-12
 */
@RestController
@Api(tags = "选项数据控制器")
@RequestMapping("/options-table")
public class OptionsTableController {

  @Resource private IOptionsTableService service;

  @ApiOperation(value = "请求所属题目的选项信息数据")
  @GetMapping("/getList")
  public CommonResult<List<OptionsTable>> getList(
      @ApiParam("所属题目") @RequestParam("belongId") Long belongId) {
    try {
      List<OptionsTable> list = service.getList(belongId);
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "根据id执行删除请求")
  @DeleteMapping("/delById")
  public CommonResult<Boolean> delById(@ApiParam("主键") @RequestParam("id") Long id) {
    try {
      boolean b = service.removeById(id);
      if (b) {
        return resultOk(true);
      }
      throw new NullPointerException();
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "更新选项是正确还是错误请求")
  @PutMapping("/updateOffOrOnById")
  public CommonResult<Boolean> updateOffOrOnById(@ApiParam("选项信息") OptionsTable entity) {
    try {
      boolean save = service.updateOffOrOnById(entity);
      if (save) {
        return resultOk(true);
      }
      throw new NullPointerException();
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "新增选项信息请求")
  @PostMapping("/create")
  public CommonResult<Boolean> create(@ApiParam("选项信息") OptionsTable entity) {
    try {
      Boolean save = service.create(entity);
      return resultOk(save);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "更新选项内容信息请求")
  @PutMapping("/update")
  public CommonResult<Boolean> update(@ApiParam("选项信息") OptionsTable entity) {
    try {
      Boolean save = service.updateById(entity);
      return resultOk(save);
    } catch (Exception e) {
      return resultError();
    }
  }
}
