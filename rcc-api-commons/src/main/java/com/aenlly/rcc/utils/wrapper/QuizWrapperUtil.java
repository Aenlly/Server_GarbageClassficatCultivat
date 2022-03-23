package com.aenlly.rcc.utils.wrapper;

import com.aenlly.rcc.entity.*;
import com.aenlly.rcc.enums.CorrectlyOrNotEnum;
import com.aenlly.rcc.enums.SubmitStateEnum;
import com.aenlly.rcc.enums.TopicStateEnum;
import com.aenlly.rcc.enums.WeekNumberEnum;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * 知识测试查询对象获取类
 *
 * @author Aenlly
 * @create by date 2021/12/17 20:56
 * @projectName RefuseClassificationCultivate
 */
public class QuizWrapperUtil {

  /**
   * 根据问卷名称 获得问卷表操作对象
   *
   * @param naireName 问卷名称
   * @return 查询对象
   */
  public static Wrapper<Questionnaire> getQuestionnaireByName(String naireName) {
    QueryWrapper<Questionnaire> wrapper = new QueryWrapper<>();
    wrapper.eq("questionnaire_name", naireName);
    return wrapper;
  }

  /**
   * 根据用户编号，问卷编号 获得答卷表操作对象
   *
   * @param userId 用户编号
   * @param questionnaireId 问卷编号
   * @param weekNumber 每周可答题的次数，1或者7
   * @return 查询对象
   */
  public static Wrapper<PaperTables> getPaperTablesExist(
      String userId, Long questionnaireId, Integer weekNumber) {
    QueryWrapper<PaperTables> wrapper = new QueryWrapper<>();
    wrapper.eq("user_id", userId).eq("belong_questionnaire_id", questionnaireId);
    if (weekNumber.equals(WeekNumberEnum.DAILY.getWeekNumber())) {
      wrapper.apply("TO_DAYS(create_time) = TO_DAYS(NOW())");
    } else if (weekNumber.equals(WeekNumberEnum.WEEK.getWeekNumber())) {
      wrapper.apply("YEARWEEK(create_time) = YEARWEEK(now())");
    }
    return wrapper;
  }

  /**
   * 根据随机组卷批次号 获得题目对应表操作对象
   *
   * @param randomBatchIndex 随机组卷批次号
   * @return 查询对象
   */
  public static Wrapper<QuestionnaireTopics> getTopicByRandomBatchIndex(String randomBatchIndex) {
    QueryWrapper<QuestionnaireTopics> wrapper = new QueryWrapper<>();
    wrapper.eq("random_batch_index", randomBatchIndex).eq("state", 0);
    return wrapper;
  }

  /**
   * 根据题库id,随机取10个数据条 获得题目表操作对象
   *
   * @param databankId 题库id
   * @param topicCount 单选题目数量
   * @return 查询对象
   */
  public static Wrapper<SubjectTable> getSubjectTableLimitTen(
      Integer databankId, Integer topicCount) {
    QueryWrapper<SubjectTable> wrapper = new QueryWrapper<>();
    wrapper.eq("databank_id", databankId).last("ORDER BY  RAND() LIMIT " + topicCount);
    return wrapper;
  }

  /**
   * 根据题目id 用于查询题目集合使用 获得选项表操作对象
   *
   * @param id 题目id
   * @return 查询对象
   */
  public static Wrapper<OptionsTable> getOptionsTableListBySubjectId(Long id) {
    QueryWrapper<OptionsTable> wrapper = new QueryWrapper<>();
    wrapper.select("id", "option_name", "belong_topic_id").eq("belong_topic_id", id);
    return wrapper;
  }

  /**
   * 根据用户编号，随机组卷批次号 获得答卷表操作对象
   *
   * @param userId 用户编号
   * @param randomIndex 随机组卷批次号
   * @return 查询对象
   */
  public static Wrapper<PaperTables> getPaperTablesOne(String userId, String randomIndex) {
    QueryWrapper<PaperTables> wrapper = new QueryWrapper<>();
    wrapper.eq("user_id", userId).eq("random_batch_index", randomIndex);
    return wrapper;
  }

  /**
   * 根据题目编号，随机组卷批次号 获得题目对应表操作对象
   *
   * @param topicId 题目编号
   * @param randomIndex 随机组卷批次号
   * @return 查询对象
   */
  public static Wrapper<QuestionnaireTopics> getQuestionnaireTopicBy(
      Long topicId, String randomIndex) {
    QueryWrapper<QuestionnaireTopics> wrapper = new QueryWrapper<>();
    wrapper.eq("belong_topic_id", topicId).eq("random_batch_index", randomIndex);
    return wrapper;
  }

  /**
   * 随机组卷批次号，获取未答题数量 获得题目对应表操作对象
   *
   * @param randomIndex 随机组卷批次号
   * @return 查询对象
   */
  public static Wrapper<QuestionnaireTopics> getQuestionnaireTopicAnswer(String randomIndex) {
    QueryWrapper<QuestionnaireTopics> wrapper = new QueryWrapper<>();
    wrapper.eq("random_batch_index", randomIndex).eq("state", TopicStateEnum.UN_ANSWERED);
    return wrapper;
  }

  /**
   * 根据题目编号 获得选项表正确答案操作对象
   *
   * @param topicId 题目编号
   * @return 查询对象
   */
  public static Wrapper<OptionsTable> getOptionsTableCorrect(Long topicId) {
    QueryWrapper<OptionsTable> wrapper = new QueryWrapper<>();
    wrapper.eq("belong_topic_id", topicId).eq("correctly_or_not", CorrectlyOrNotEnum.CPRRECTLY);
    return wrapper;
  }

  /**
   * 根据用户编号、问卷名称 获得操作对象
   *
   * @param userId 用户编号
   * @param questionnaireName 问卷名称
   * @return 查询对象
   */
  public static Wrapper<PaperTables> getPaperTablesLog(String userId, String questionnaireName) {
    QueryWrapper<PaperTables> wrapper = new QueryWrapper<>();
    wrapper
        .select("id", "submit_time", "total_score")
        .eq("user_id", userId)
        .eq("belong_questionnaire_name", questionnaireName)
        .eq("state", SubmitStateEnum.SUBMITTED)
        .orderByDesc("submit_time");
    return wrapper;
  }
}
