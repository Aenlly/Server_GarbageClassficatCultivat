package com.aenlly.rcc.admin.controller;

import com.aenlly.rcc.entity.OptionsTable;
import com.aenlly.rcc.service.IOptionsTableService;
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

  @ApiOperation(value = "请求所属题目的选项信息数据", httpMethod = "GET")
  @GetMapping("/getList")
  public CommonResult<List<OptionsTable>> getList(
      @Param("所属题目") @RequestParam("belongId") Long belongId) {
    try {
      List<OptionsTable> list = service.getList(belongId);
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

  @ApiOperation(value = "更新选项信息请求", httpMethod = "PUT")
  @PutMapping("/update")
  public CommonResult<Boolean> update(@Param("选项信息") OptionsTable entity) {
    try {
      Boolean save = service.updateById(entity);
      return resultOk(save);
    } catch (Exception e) {
      return resultError();
    }
  }
}
