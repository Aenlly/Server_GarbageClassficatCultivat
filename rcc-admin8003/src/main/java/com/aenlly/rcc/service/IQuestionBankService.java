package com.aenlly.rcc.service;

import com.aenlly.rcc.entity.ItemPoolSubjectView;
import com.aenlly.rcc.entity.ItemPoolTable;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 题库管理 服务类
 *
 * @author aenlly
 * @since 2022-02-04
 */
public interface IQuestionBankService {

  /**
   * 查询信息集合
   *
   * @param page 分页对象
   * @return 分页对象
   */
  IPage<ItemPoolSubjectView> getList(Page<ItemPoolSubjectView> page);

  /**
   * 更新信息到数据库中
   *
   * @param entity 更新实体
   * @return 是否成功
   */
  Boolean update(ItemPoolTable entity);
}
