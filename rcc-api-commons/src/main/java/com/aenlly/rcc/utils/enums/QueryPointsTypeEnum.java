package com.aenlly.rcc.utils.enums;

import com.aenlly.rcc.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 积分排名的积分类型枚举类
 *
 * @author Aenlly
 * @create by date 2022/02/14 23:34
 * @projectName RefuseClassificationCultivate
 */
@Getter
@AllArgsConstructor
public enum QueryPointsTypeEnum implements BaseEnum {

  /** 根据累积积分排序，默认 */
  CUMULATE(0, "accumulative_points"),
  /** 根据答题积分排序 */
  ANSWER(1, "answer_points"),
  /** 根据当前剩余积分排序 */
  CURRENT(2, "remaining_points");

  private Integer name;
  private String value;
}
