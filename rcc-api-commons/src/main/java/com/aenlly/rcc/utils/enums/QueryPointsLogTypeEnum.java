package com.aenlly.rcc.utils.enums;

import com.aenlly.rcc.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 积分记录查询类型枚举类
 *
 * @author Aenlly
 * @create by date 2022/01/30 18:49
 * @projectName RefuseClassificationCultivate
 */
@AllArgsConstructor
@Getter
public enum QueryPointsLogTypeEnum implements BaseEnum {

  /** 根据积分记录描述查询 */
  ORDER_ID(0, "log_desc"),
  /** 根据用户编号查询 */
  GIFT_NAME(1, "user_id");

  private Integer name;
  private String value;
}
