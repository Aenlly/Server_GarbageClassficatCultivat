package com.aenlly.rcc.user.utils;

import com.aenlly.rcc.entity.PaperTables;
import com.aenlly.rcc.entity.QuestionnaireTopics;
import com.aenlly.rcc.utils.CommonResult;

import java.util.List;

/**
 * 知识测验 服务类
 *
 * @author Aenlly
 * @create by date 2021/12/17 20:45
 * @projectName RefuseClassificationCultivate
 */
public interface IAnswerQuestionService {
  /**
   * 根据用户编号，与问卷名称，生成题目
   *
   * @param userId 用户编号
   * @param naireName 问卷名称，每日答题或分类小考
   * @return 返回内容，包含返回码
   */
  CommonResult<String> getRandomBatchIndex(String userId, String naireName);

  /**
   * 根据用户编号与随机组卷批次号，获得题目列表
   *
   * @param userId 用户编号
   * @param randomIndex 随机组卷批次号
   * @return 题目列表
   */
  List<QuestionnaireTopics> getTopics(String userId, String randomIndex);

  /**
   * 根据用户编号，题目编号，所选选项id，随机组卷批次号 执行选择选项流程判断
   *
   * @param userId 用户编号
   * @param topicId 题目编号
   * @param optionId 所选选项id
   * @param randomIndex 随机祖居批次号
   * @return 正确选项编号
   */
  Long isCorrect(String userId, Long topicId, Long optionId, String randomIndex);

  /**
   * 根据用户编号，随机组卷批次号 执行交卷流程
   *
   * @param userId 用户编号
   * @param randomIndex 随机组卷批次号
   * @return 交卷成功
   */
  Boolean submitPaper(String userId, String randomIndex);

  /**
   * 根据用户编号，问卷类型 执行请求答题记录数据
   *
   * @param userId 用户编号
   * @param questionnaireName 问卷类型
   * @return 答题记录列表
   */
  List<PaperTables> getPaperTablesLog(String userId, String questionnaireName);
}
