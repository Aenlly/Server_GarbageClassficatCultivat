package com.aenlly.rcc.service;

import com.aenlly.rcc.entity.Questionnaire;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 问卷表 服务类
 *
 * @author aenlly
 * @since 2022-02-13
 */
public interface IQuestionnaireService extends IService<Questionnaire> {

  /**
   * 查询问卷表信息分页集合
   *
   * @param page 分页对象
   * @return 分页对象
   */
  IPage<Questionnaire> getList(Page<Questionnaire> page);
}
