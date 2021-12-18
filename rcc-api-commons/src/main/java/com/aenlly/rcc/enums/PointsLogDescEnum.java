package com.aenlly.rcc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 积分描述常用常量
 *
 * @author Aenlly
 * @create by date 2021/12/16 15:22
 * @projectName RefuseClassificationCultivate
 */
@AllArgsConstructor
@Getter
public enum PointsLogDescEnum {
  /** 每日签到 */
  DAILY_CHECK("每日签到", 2),
  /** 每日答题,计算时需要得分除该数 */
  DAILY_ANSWER("每日答题", 10),
  /** 分类小考,计算时需要得分除该数 */
  CLASS_QUIZ("分类小考", 2);

  private String value;
  private Integer points;
}
