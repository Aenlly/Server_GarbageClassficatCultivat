package com.aenlly.rcc.user.utils.impl;

import com.aenlly.rcc.entity.*;
import com.aenlly.rcc.enums.CorrectlyOrNotEnum;
import com.aenlly.rcc.enums.PointsLogDescEnum;
import com.aenlly.rcc.enums.SubmitStateEnum;
import com.aenlly.rcc.enums.TopicStateEnum;
import com.aenlly.rcc.service.IOptionsTableService;
import com.aenlly.rcc.user.service.*;
import com.aenlly.rcc.user.utils.IAnswerQuestionService;
import com.aenlly.rcc.utils.CommonResult;
import com.aenlly.rcc.utils.wrapper.QuizWrapperUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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
public class AnswerQuestionServiceImpl implements IAnswerQuestionService {

  /** 问卷表-服务对象 */
  @Resource IQuestionnaireService questionnaireService;
  /** 答卷-答案表-服务对象 */
  @Resource IAnswerSheetService answerSheetService;
  /** 问卷题目对应表-服务对象 */
  @Resource IQuestionnaireTopicsService questionnaireTopicsService;
  /** 答卷表-服务对象 */
  @Resource IPaperTablesService paperTablesService;
  /** 题目表-服务对象 */
  @Resource ISubjectTableService subjectTableService;
  /** 选项表-服务对象 */
  @Resource IOptionsTableService optionsTableService;
  /** 用户表-服务对象 */
  @Resource IUserService userService;
  /** 积分记录表-服务对象 */
  @Resource IPointsLogService pointsLogService;

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
        // 今日已进行答题，同时未提交时，则执行交卷流程
        if (paperTables.getState().equals(SubmitStateEnum.NOT_SUBMITTED)) {
          submitPaper(userId, paperTables.getRandomBatchIndex());
        }
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
   * 根据用户编号，题目编号，所选选项id，随机组卷批次号 执行选择选项流程判断
   *
   * @param userId 用户编号
   * @param topicId 题目编号
   * @param optionId 所选选项id
   * @param randomIndex 随机祖居批次号
   * @return 正确选项编号
   */
  @Override
  @Transactional
  public Long isCorrect(String userId, Long topicId, Long optionId, String randomIndex) {

    OptionsTable optionsTableById = optionsTableService.getById(optionId);

    // 获得题目对应表的操作对象
    Wrapper<QuestionnaireTopics> questionnaireTopicBy =
        QuizWrapperUtil.getQuestionnaireTopicBy(topicId, randomIndex);
    // 获得题目对应表信息
    QuestionnaireTopics questionnaireTopics =
        questionnaireTopicsService.getOne(questionnaireTopicBy);
    // 设置题目状态为已答
    questionnaireTopics.setState(TopicStateEnum.ANSWERED);
    // 修改答题对应表题目状态信息
    boolean b = questionnaireTopicsService.updateById(questionnaireTopics);

    // 获得答卷操作对象
    Wrapper<PaperTables> paperTablesOne = QuizWrapperUtil.getPaperTablesOne(userId, randomIndex);
    // 查询答卷信息
    PaperTables paperTables = paperTablesService.getOne(paperTablesOne);
    // 设置未答数量减一
    paperTables.setUnAnsweredAmount(paperTables.getUnAnsweredAmount() - 1);

    // 创建答卷-答案表对象
    AnswerSheet answerSheet = new AnswerSheet();

    // 选项错误
    if (optionsTableById.getCorrectlyOrNot().equals(CorrectlyOrNotEnum.NOT)) {
      // 设置答错数量+1
      paperTables.setErrorAmount(paperTables.getErrorAmount() + 1);
      // 设置选项得分
      answerSheet.setScore(0);
    } else {
      // 设置答对数量+1
      paperTables.setRightAmount(paperTables.getRightAmount() + 1);
      // 总分相加
      paperTables.setTotalScore(paperTables.getTotalScore() + questionnaireTopics.getScore());
      // 设置选项得分
      answerSheet.setScore(questionnaireTopics.getScore());
    }
    boolean isPaper = paperTablesService.updateById(paperTables);

    // 设置选项名称
    answerSheet.setSelectedOptionName(optionsTableById.getOptionName());
    // 设置选项id
    answerSheet.setSelectedOptionId(optionsTableById.getId());
    // 设置选项是否正确
    answerSheet.setAnswerResults(optionsTableById.getCorrectlyOrNot());
    // 所属题目名称
    answerSheet.setBelongTopicName(questionnaireTopics.getBelongTopicName());
    // 所属题目id
    answerSheet.setBelongTopicId(questionnaireTopics.getBelongTopicId());
    // 设置所属答卷
    answerSheet.setBelongPaperTables(paperTables.getId());
    // 创建答卷-答案表信息
    boolean save = answerSheetService.save(answerSheet);

    // 获得选型表正确选项操作对象
    Wrapper<OptionsTable> optionsTableCorrect = QuizWrapperUtil.getOptionsTableCorrect(topicId);
    // 获得选项表正确信息
    OptionsTable optionsTable = optionsTableService.getOne(optionsTableCorrect);

    // 更新失败或者保存失败，抛出异常
    if (!b || !isPaper || !save) {
      throw new NullPointerException();
    }
    return optionsTable.getId();
  }

  /**
   * 根据用户编号，随机组卷批次号 执行交卷流程
   *
   * @param userId 用户编号
   * @param randomIndex 随机组卷批次号
   * @return 是否交卷
   */
  @Override
  @Transactional
  public Boolean submitPaper(String userId, String randomIndex) {

    // 获得答卷表操作对象
    Wrapper<PaperTables> paperTablesOne = QuizWrapperUtil.getPaperTablesOne(userId, randomIndex);
    // 获得答卷表信息对象
    PaperTables paperTables = paperTablesService.getOne(paperTablesOne);
    // 设置答卷对象状态为已提交
    paperTables.setState(SubmitStateEnum.SUBMITTED);
    // 设置提交时间
    paperTables.setSubmitTime(LocalDateTime.now());
    // 更新答卷信息
    boolean update = paperTablesService.updateById(paperTables);
    // 获取答卷的问卷名称
    String questionnaireName = paperTables.getBelongQuestionnaireName();
    // 创建
    int points = 0;
    PointsLogDescEnum pointsLogDescEnum = PointsLogDescEnum.DAILY_ANSWER;
    // 判断问卷类型，每日或分类小考
    if (questionnaireName.equals(PointsLogDescEnum.DAILY_ANSWER.getValue())) {
      // 根据得分计算获取积分
      points = paperTables.getTotalScore() / PointsLogDescEnum.DAILY_ANSWER.getPoints();
    } else if (questionnaireName.equals(PointsLogDescEnum.CLASS_QUIZ.getValue())) {
      // 根据得分计算获取积分
      points = paperTables.getTotalScore() / PointsLogDescEnum.CLASS_QUIZ.getPoints();
      pointsLogDescEnum = PointsLogDescEnum.CLASS_QUIZ;
    }
    // 增加积分记录与用户积分信息
    boolean question = pointsLogService.answerQuestion(userId, points, pointsLogDescEnum);

    if (!update || !question) {
      throw new NullPointerException();
    }
    return true;
  }

  /**
   * 根据用户编号，问卷类型 执行请求答题记录数据
   *
   * @param userId 用户编号
   * @param questionnaireName 问卷类型
   * @return 答题记录列表
   */
  @Override
  public List<PaperTables> getPaperTablesLog(String userId, String questionnaireName) {
    Wrapper<PaperTables> paperTablesLog =
        QuizWrapperUtil.getPaperTablesLog(userId, questionnaireName);
    return paperTablesService.list(paperTablesLog);
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
      questionnaireTopics.setBelongTopicName(subjectTable.getTopicName());
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
