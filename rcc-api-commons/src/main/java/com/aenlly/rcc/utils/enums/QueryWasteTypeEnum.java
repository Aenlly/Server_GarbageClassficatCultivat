package com.aenlly.rcc.utils.enums;

import com.aenlly.rcc.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Aenlly
 * @create by date 2022/01/31 15:56
 * @projectName RefuseClassificationCultivate
 */
@Getter
@AllArgsConstructor
public enum QueryWasteTypeEnum implements BaseEnum {
  /** 根据标题查询 */
  TITLE(0, "text"),
  /** 根据描述查询 */
  DESC(1, "text_desc");

  private Integer name;
  private String value;
}
