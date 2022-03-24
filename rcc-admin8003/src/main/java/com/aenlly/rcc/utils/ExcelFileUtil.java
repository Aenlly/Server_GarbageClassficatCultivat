package com.aenlly.rcc.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 表格文件导入导出处理工具类
 *
 * @author Aenlly
 * @create by date 2022/02/12 17:14
 * @projectName RefuseClassificationCultivate
 */
public class ExcelFileUtil {

  /**
   * 导出模板文件
   *
   * @param response 响应对象
   * @param pojoClass 推断类型
   * @throws IOException 异常
   */
  public static <T> void exportExcel(HttpServletResponse response, Class<T> pojoClass)
      throws IOException {
    // 设置响应输出的头类型(设置响应类型)
    response.setHeader("content-Type", "application/force-download");
    // 下载文件的默认名称(设置下载文件的默认名称)
    response.setHeader(
        "Content-Disposition",
        "attachment;filename=" + URLEncoder.encode("导入模板.xls", StandardCharsets.UTF_8));
    ExportParams exportParams = new ExportParams("提示：文本长度不能大于200个字符(上传时该行删除)", "题目导入");
    // 导出操作
    Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, new ArrayList<>());
    workbook.write(response.getOutputStream());
    workbook.close();
  }

  /**
   * 导入文件解析
   *
   * @param file 文件
   * @param pojoClass 推断类
   * @return 解析集合
   */
  public static <T> List<T> importExcelFile(MultipartFile file, Class<T> pojoClass) {
    ImportParams params = new ImportParams();
    params.setConcurrentTask(true);
    List<T> list = null;
    try {
      list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
}
