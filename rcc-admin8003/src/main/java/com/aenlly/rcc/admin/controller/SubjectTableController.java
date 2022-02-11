package com.aenlly.rcc.admin.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.aenlly.rcc.admin.service.ISubjectTableService;
import com.aenlly.rcc.entity.SubjectTable;
import com.aenlly.rcc.execl.QuizSubjectExecl;
import com.aenlly.rcc.utils.CommonResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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

  @ApiOperation(value = "获得题目导入模板")
  @GetMapping("/getExcelFile")
  public void getExcelFile(HttpServletResponse response) {
    // 设置响应输出的头类型(设置响应类型)
    response.setHeader("content-Type", "application/force-download");
    try {
      // 下载文件的默认名称(设置下载文件的默认名称)
      response.setHeader(
          "Content-Disposition",
          "attachment;filename=" + URLEncoder.encode("题目导入模板.xls", StandardCharsets.UTF_8));
      ExportParams exportParams = new ExportParams("提示：文本长度不能大于200个字符(上传时该行删除)", "题目导入");
      // 设置表头颜色
      exportParams.setColor(HSSFColor.HSSFColorPredefined.YELLOW.getIndex());
      // 列宽自动适用
      exportParams.setAutoSize(true);
      // 导出操作
      Workbook workbook =
          ExcelExportUtil.exportExcel(exportParams, QuizSubjectExecl.class, new ArrayList<>());
      workbook.write(response.getOutputStream());
      workbook.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
