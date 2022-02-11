package com.aenlly.rcc.admin.service.impl;

import com.aenlly.rcc.admin.service.ISubjectTableService;
import com.aenlly.rcc.entity.SubjectTable;
import com.aenlly.rcc.mapper.SubjectTableMapper;
import com.aenlly.rcc.utils.wrapper.SubjectTableWrapperUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 题目表 服务实现类
 *
 * @author aenlly
 * @since 2022-02-11
 */
@Service
public class SubjectTableServiceImpl extends ServiceImpl<SubjectTableMapper, SubjectTable>
    implements ISubjectTableService {

  /**
   * 查询信息集合
   *
   * @param page 分页对象
   * @param belongId 所属题库id
   * @param text 查询内容
   * @return 分页对象
   */
  @Override
  public IPage<SubjectTable> getList(Page<SubjectTable> page, Long belongId, String text) {
    Wrapper<SubjectTable> wrapper = SubjectTableWrapperUtil.queryListPage(belongId, text);
    return this.page(page, wrapper);
  }
}
