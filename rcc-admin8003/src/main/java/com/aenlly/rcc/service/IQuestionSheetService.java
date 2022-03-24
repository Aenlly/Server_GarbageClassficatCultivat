package com.aenlly.rcc.service;

import com.aenlly.rcc.entity.AnswerSheet;
import com.aenlly.rcc.entity.PaperTables;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * 答卷信息管理 服务类
 *
 * @author Aenlly
 * @create by date 2022/02/13 15:38
 * @projectName RefuseClassificationCultivate
 */
public interface IQuestionSheetService {

  /**
   * 查询答卷表信息集合
   *
   * @param page 分页对象
   * @param belongId 所属问卷id
   * @param startTime 开始时间
   * @param endTime 结束时间
   * @return 分页对象
   */
  IPage<PaperTables> getList(
      Page<PaperTables> page, Long belongId, String startTime, String endTime);

  /**
   * 工根据所属答卷id查询答卷-答案表信息集合
   *
   * @param belongPaperId 所属答卷id
   * @return 结果集合
   */
  List<AnswerSheet> getListByBelongPaperId(Long belongPaperId);
}
