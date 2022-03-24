package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.service.IQuestionSheetService;
import com.aenlly.rcc.entity.AnswerSheet;
import com.aenlly.rcc.entity.PaperTables;
import com.aenlly.rcc.service.IAnswerSheetService;
import com.aenlly.rcc.service.IPaperTablesService;
import com.aenlly.rcc.utils.wrapper.QuestionSheetWrapperUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 答卷信息管理 服务实现类
 *
 * @author Aenlly
 * @create by date 2022/02/13 15:39
 * @projectName RefuseClassificationCultivate
 */
@Service
public class QuestionSheetServiceImpl implements IQuestionSheetService {
  /** 答卷表服务类对象 */
  @Resource private IPaperTablesService paperTablesService;

  /** 答卷-答案表服务类对象 */
  @Resource private IAnswerSheetService answerSheetService;

  /**
   * 查询答卷表信息集合
   *
   * @param page 分页对象
   * @param belongId 所属问卷id
   * @param startTime 开始时间
   * @param endTime 结束时间
   * @return 分页对象
   */
  @Override
  public IPage<PaperTables> getList(
      Page<PaperTables> page, Long belongId, String startTime, String endTime) {
    Wrapper<PaperTables> wrapper =
        QuestionSheetWrapperUtil.queryListPage(belongId, startTime, endTime);
    return paperTablesService.page(page, wrapper);
  }

  /**
   * 查询答卷表信息集合
   *
   * @param belongPaperId 所属答卷id
   * @return 分页对象
   */
  @Override
  public List<AnswerSheet> getListByBelongPaperId(Long belongPaperId) {
    Wrapper<AnswerSheet> wrapper = QuestionSheetWrapperUtil.queryListByBelongPaperId(belongPaperId);
    return answerSheetService.list(wrapper);
  }
}
