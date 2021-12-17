package com.aenlly.rcc.utils.impl;

import com.aenlly.rcc.entity.*;
import com.aenlly.rcc.service.*;
import com.aenlly.rcc.utils.AnswerQuestionService;
import com.aenlly.rcc.utils.CommonResult;
import com.aenlly.rcc.utils.wrapper.QuizWrapperUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.aenlly.rcc.utils.ResultUtil.resultExist;
import static com.aenlly.rcc.utils.ResultUtil.resultOk;

/**
 * 知识测验 服务实现类
 *
 * @author Aenlly
 * @create by date 2021/12/17 20:46
 * @projectName RefuseClassificationCultivate
 */
@Service
public class AnswerQuestionServiceImpl implements AnswerQuestionService {

  /** 问卷表-服务对象 */
  @Resource IQuestionnaireService questionnaireService;

  /** 问卷题目对应表-服务对象 */
  @Resource IQuestionnaireTopicsService questionnaireTopicsService;
  /** 答卷表-服务对象 */
  @Resource IPaperTablesService paperTablesService;
  /** 题目表-服务对象 */
  @Resource ISubjectTableService subjectTableService;
  /** 选项表-服务对象 */
  @Resource IOptionsTableService optionsTableService;

  /**
   * 根据用户编号，与问卷名称，生成题目
   *
   * @param userId 用户编号
   * @param naireName 问卷名称，每日答题或分类小考
   * @return 返回随机组卷批次号，包含返回码
   */
  @Override
  @Transactional // 开启事务
  public CommonResult<String> getRandomBatchIndex(String userId, String naireName) {
    // 获取问卷查询对象
    Wrapper<Questionnaire> byName = QuizWrapperUtil.getQuestionnaireByName(naireName);
    // 获得获得问卷对象
    Questionnaire one = questionnaireService.getOne(byName);
    // 判断问卷是否为空，为空抛出异常
    isNotNull(one);
    // 获得查询当前用户是否存在答卷-操作对象
    Wrapper<PaperTables> paperTablesExist =
        QuizWrapperUtil.getPaperTablesExist(
            userId, one.getId(), one.getWeekNumber().getWeekNumber());
    // 判断是否存在
    long count = paperTablesService.count(paperTablesExist);
    // 判断今日是否进行了答题，存在则返回
    if (count > 0) {
      // 获得答卷信息
      PaperTables paperTables = paperTablesService.getOne(paperTablesExist);
      // 判断是否存在未答题目
      if (paperTables.getUnAnsweredAmount() > 0) {
        return resultOk(paperTables.getRandomBatchIndex());
      } else {
        // 代表今日已完成
        return resultExist();
      }
    } else {
      String randomBatchIndex = randomGroup(userId, one);
      return resultOk(randomBatchIndex);
    }
  }

  /**
   * 根据用户编号与随机组卷批次号，获得题目列表
   *
   * @param userId 用户编号
   * @param randomIndex 随机组卷批次号
   * @return 题目列表
   */
  @Override
  public List<QuestionnaireTopics> getTopics(String userId, String randomIndex) {
    // 获得查询问卷信息对象
    Wrapper<QuestionnaireTopics> topicByRandomBatchIndex =
        QuizWrapperUtil.getTopicByRandomBatchIndex(randomIndex);
    // 获得题目列表
    List<QuestionnaireTopics> list = questionnaireTopicsService.list(topicByRandomBatchIndex);

    for (QuestionnaireTopics questionnaireTopics : list) {
      // 构造选项查询对象
      Wrapper<OptionsTable> optionsTableListBySubjectIds =
          QuizWrapperUtil.getOptionsTableListBySubjectId(questionnaireTopics.getBelongTopicId());
      // 查询选项集合
      List<OptionsTable> optionsTableList = optionsTableService.list(optionsTableListBySubjectIds);

      // 查询题目详细信息
      SubjectTable subjectTable =
          subjectTableService.getById(questionnaireTopics.getBelongTopicId());
      // 设置选项信息
      questionnaireTopics.setOptionsTables(optionsTableList);
      // 设置解析信息
      questionnaireTopics.setAnalysis(subjectTable.getAnalysis());
    }
    return list;
  }

  /**
   * 生成题目对应表与答卷表
   *
   * @param userId 用户编号
   * @param entity 问卷表对象
   * @return 随机组卷批次号
   */
  @Transactional
  public String randomGroup(String userId, Questionnaire entity) {
    // 生成随机组卷批次号
    String randomBatchIndex = UUID.randomUUID().toString();
    Wrapper<SubjectTable> subjectTableLimitTen =
        QuizWrapperUtil.getSubjectTableLimitTen(entity.getDatabankId());
    // 随机取十条题目数据
    List<SubjectTable> list = subjectTableService.list(subjectTableLimitTen);

    // 创建问卷题目对应表list，用于批量插入
    List<QuestionnaireTopics> topicsList = new ArrayList<>();

    for (SubjectTable subjectTable : list) {

      QuestionnaireTopics questionnaireTopics = new QuestionnaireTopics();

      // 设置问卷名称
      questionnaireTopics.setQuestionnaireName(entity.getQuestionnaireName());
      // 设置问卷id
      questionnaireTopics.setQuestionnaireId(entity.getId());
      // 设置题目编号
      questionnaireTopics.setBelongTopicId(subjectTable.getId());
      // 设置题目名称
      questionnaireTopics.setBelongTopic(subjectTable.getTopicName());
      // 设置随机组卷批次号
      questionnaireTopics.setRandomBatchIndex(randomBatchIndex);
      // 添加到list中
      topicsList.add(questionnaireTopics);
    }
    boolean b = questionnaireTopicsService.saveBatch(topicsList);
    if (b) {
      // 创建答卷表对象
      PaperTables paperTables = new PaperTables();
      paperTables.setUserId(userId);
      paperTables.setBelongQuestionnaireId(entity.getId());
      paperTables.setBelongQuestionnaireName(entity.getQuestionnaireName());
      paperTables.setRandomBatchIndex(randomBatchIndex);
      // 插入答卷表信息
      boolean save = paperTablesService.save(paperTables);
      if (save) {
        return randomBatchIndex;
      }
    }
    throw new NullPointerException();
  }

  /**
   * 判断是否为空
   *
   * @param t 判断类型
   */
  private <T> void isNotNull(T t) {
    if (t == null || t.equals("")) {
      throw new NullPointerException();
    }
  }
}
