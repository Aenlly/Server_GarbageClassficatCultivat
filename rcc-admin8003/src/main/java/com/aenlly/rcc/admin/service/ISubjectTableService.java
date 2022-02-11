package com.aenlly.rcc.admin.service;

import com.aenlly.rcc.entity.SubjectTable;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
