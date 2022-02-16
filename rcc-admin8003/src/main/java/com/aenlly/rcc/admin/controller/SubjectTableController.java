package com.aenlly.rcc.admin.controller;

import com.aenlly.rcc.admin.service.ISubjectTableService;
import com.aenlly.rcc.entity.SubjectTable;
import com.aenlly.rcc.utils.CommonResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
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
@RequestMapping("/subject-table")
public class SubjectTableController {

  @Resource private ISubjectTableService service;

  @ApiOperation(value = "请求题目信息数据", httpMethod = "GET")
  @GetMapping("/getList")
  public CommonResult<IPage<SubjectTable>> getList(
      @Param("当前页码") @RequestParam("current") Integer current,
      @Param("每页数量") @RequestParam("size") Integer size,
      @Param("所属题库id") @RequestParam("belongId") Long belongId,
      @Param("查询内容") @RequestParam("text") String text) {
    try {
      IPage<SubjectTable> list = service.getList(new Page<>(current, size), belongId, text);
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

  @ApiOperation(value = "上传题目导入文件", httpMethod = "POST")
  @PostMapping("/uploadExcelFile/{belongId}")
  public CommonResult<Boolean> uploadExcelFile(
      @Param("题目文件") @RequestPart("excelFile") MultipartFile file,
      @Param("所属题库") @PathVariable("belongId") Long belongId) {
    try {
      Boolean b = service.uploadExcelFile(file, belongId);
      return resultOk(b);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "获得题目导入模板", httpMethod = "GET")
  @GetMapping("/getExcelFile")
  public void getExcelFile(HttpServletResponse response) {
    try {
      service.getExcelFile(response);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @ApiOperation(value = "新增选项信息请求", httpMethod = "POST")
  @PostMapping("/create")
  public CommonResult<Boolean> create(@Param("选项信息") SubjectTable entity) {
    try {
      Boolean save = service.save(entity);
      return resultOk(save);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "更新题目数据请求", httpMethod = "PUT")
  @PutMapping("/update")
  public CommonResult<Boolean> update(@Param("选项信息") SubjectTable entity) {
    try {
      Boolean save = service.updateById(entity);
      return resultOk(save);
    } catch (Exception e) {
      return resultError();
    }
  }
}
