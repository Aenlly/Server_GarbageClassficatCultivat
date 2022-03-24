package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.SubjectTable;
import com.aenlly.rcc.service.ISubjectTableService;
import com.aenlly.rcc.utils.CommonResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.aenlly.rcc.utils.ResultUtil.resultError;
import static com.aenlly.rcc.utils.ResultUtil.resultOk;

/**
 * 题目表 前端控制器
 *
 * @author aenlly
 * @since 2022-02-11
 */
@RestController
@Api(tags = "题目控制器")
@RequestMapping("/subject-table")
public class SubjectTableController {

  @Resource private ISubjectTableService service;

  @ApiOperation(value = "请求题目信息数据")
  @GetMapping("/getList")
  public CommonResult<IPage<SubjectTable>> getList(
      @ApiParam("当前页码") @RequestParam("current") Integer current,
      @ApiParam("每页数量") @RequestParam("size") Integer size,
      @ApiParam("所属题库id") @RequestParam("belongId") Long belongId,
      @ApiParam("查询内容") @RequestParam("text") String text) {
    try {
      IPage<SubjectTable> list = service.getList(new Page<>(current, size), belongId, text);
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
        return resultOk(b);
      } else {
        throw new NullPointerException();
      }
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "根据id集合执行批量删除请求")
  @DeleteMapping("/delByIds")
  public CommonResult<Boolean> delByIds(@ApiParam("主键集合") @RequestBody List<Long> ids) {
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

  @ApiOperation(value = "上传题目导入文件")
  @PostMapping("/uploadExcelFile/{belongId}")
  public CommonResult<Boolean> uploadExcelFile(
      @ApiParam("题目文件") @RequestPart("excelFile") MultipartFile file,
      @ApiParam("所属题库") @PathVariable("belongId") Long belongId) {
    try {
      Boolean b = service.uploadExcelFile(file, belongId);
      return resultOk(b);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "获得题目导入模板")
  @GetMapping("/getExcelFile")
  public void getExcelFile(HttpServletResponse response) {
    try {
      service.getExcelFile(response);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @ApiOperation(value = "新增题目信息请求")
  @PostMapping("/create")
  public CommonResult<Boolean> create(@ApiParam("题目信息") SubjectTable entity) {
    try {
      Boolean save = service.save(entity);
      return resultOk(save);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "更新题目数据请求")
  @PutMapping("/update")
  public CommonResult<Boolean> update(@ApiParam("题目信息") SubjectTable entity) {
    try {
      Boolean save = service.updateById(entity);
      return resultOk(save);
    } catch (Exception e) {
      return resultError();
    }
  }
}
