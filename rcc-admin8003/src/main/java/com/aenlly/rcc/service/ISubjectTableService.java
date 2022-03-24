package com.aenlly.rcc.service;

import com.aenlly.rcc.entity.SubjectTable;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 题目表 服务类
 *
 * @author aenlly
 * @since 2022-02-11
 */
public interface ISubjectTableService extends IService<SubjectTable> {

  /**
   * 查询信息集合
   *
   * @param page 分页对象
   * @param belongId 所属题库id
   * @param text 查询内容
   * @return 分页对象
   */
  IPage<SubjectTable> getList(Page<SubjectTable> page, Long belongId, String text);

  /**
   * 导出题目导入模板给用户
   *
   * @param response 用于传输
   * @throws IOException 文件创建异常
   */
  void getExcelFile(HttpServletResponse response) throws IOException;

  /**
   * 上传文件进行解析并填充到数据库中
   *
   * @param file 文件
   * @param belongId 所属题库
   * @return 是否成功
   */
  Boolean uploadExcelFile(MultipartFile file, Long belongId);
}
