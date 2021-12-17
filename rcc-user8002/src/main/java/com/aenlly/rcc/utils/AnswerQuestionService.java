package com.aenlly.rcc.utils;

import com.aenlly.rcc.entity.QuestionnaireTopics;

import java.util.List;

/**
 * 知识测验 服务类
 *
 * @author Aenlly
 * @create by date 2021/12/17 20:45
 * @projectName RefuseClassificationCultivate
 */
public interface AnswerQuestionService {
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
}
