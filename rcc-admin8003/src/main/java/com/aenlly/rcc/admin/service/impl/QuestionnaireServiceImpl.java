package com.aenlly.rcc.admin.service.impl;

import com.aenlly.rcc.admin.service.IQuestionnaireService;
import com.aenlly.rcc.entity.Questionnaire;
import com.aenlly.rcc.mapper.QuestionnaireMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 问卷表 服务实现类
 *
 * @author aenlly
 * @since 2022-02-13
 */
@Service
public class QuestionnaireServiceImpl extends ServiceImpl<QuestionnaireMapper, Questionnaire>
    implements IQuestionnaireService {
  /**
   * 查询问卷表信息分页集合
   *
   * @param page 分页对象
   * @return 分页对象
   */
  @Override
  public IPage<Questionnaire> getList(Page<Questionnaire> page) {
    return this.page(page);
  }
}
