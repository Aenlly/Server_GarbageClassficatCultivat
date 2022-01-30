package com.aenlly.rcc.utils.enums;

import com.aenlly.rcc.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单信息查询类型枚举类
 *
 * @author Aenlly
 * @create by date 2022/01/17 16:19
 * @projectName RefuseClassificationCultivate
 */
@AllArgsConstructor
@Getter
public enum QueryOrderTypeEnum implements BaseEnum {
  /** 根据订单编号查询 */
  ORDER_ID(0, "o.order_id"),
  /** 根据礼品名称查询 */
  GIFT_NAME(1, "g.gift_name"),
  /** 根据兑换码查询 */
  CODE(2, "gi.code");

  private Integer name;
  private String value;
}
