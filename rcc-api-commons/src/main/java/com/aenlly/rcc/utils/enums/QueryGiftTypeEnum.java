package com.aenlly.rcc.utils.enums;

import com.aenlly.rcc.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Aenlly
 * @create by date 2022/01/17 22:30
 * @projectName RefuseClassificationCultivate
 */
@Getter
@AllArgsConstructor
public enum QueryGiftTypeEnum implements BaseEnum {

  /** 根据礼品名称查询 */
  GIFT_NAME(0, "gift_name"),
  /** 根据礼品描述查询 */
  GIFT_DESC(1, "gift_desc");

  private Integer name;
  private String value;
}
