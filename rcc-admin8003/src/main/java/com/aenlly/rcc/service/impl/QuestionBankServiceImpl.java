package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.entity.ItemPoolSubjectView;
import com.aenlly.rcc.entity.ItemPoolTable;
import com.aenlly.rcc.service.IItemPoolSubjectViewService;
import com.aenlly.rcc.service.IItemPoolTableService;
import com.aenlly.rcc.service.IQuestionBankService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 题库管理 服务实现类
 *
 * @author aenlly
 * @since 2022-02-04
 */
@Service
public class QuestionBankServiceImpl implements IQuestionBankService {

  /** 题库与题库多表的服务对象 */
  @Resource private IItemPoolSubjectViewService poolSubjectViewService;
  /** 题库表服务对象 */
  @Resource private IItemPoolTableService iItemPoolTableService;

  /**
   * 查询信息集合
   *
   * @param page 分页对象
   * @return 分页对象
   */
  @Override
  public IPage<ItemPoolSubjectView> getList(Page<ItemPoolSubjectView> page) {
    return poolSubjectViewService.page(page);
  }

  /**
   * 更新信息到数据库中
   *
   * @param entity 更新实体
   * @return 是否成功
   */
  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {UnsupportedOperationException.class, NullPointerException.class, RuntimeException.class})
  public Boolean update(ItemPoolTable entity) {
    return iItemPoolTableService.updateById(entity);
  }
}
