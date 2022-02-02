package com.aenlly.rcc.utils.enums;

import com.aenlly.rcc.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 热门资讯查询条件类型枚举类
 *
 * @author Aenlly
 * @create by date 2022/02/03 1:28
 * @projectName RefuseClassificationCultivate
 */
@Getter
@AllArgsConstructor
public enum QueryHotInfoTypeEnum implements BaseEnum {

  /** 根据标题查询 */
  TITLE(0, "hot_info_title"),
  /** 根据描述查询 */
  DESC(1, "hot_info_desc"),
  /** 根据来源查询 */
  SOURCE(2, "source_text");

  private Integer name;
  private String value;
}
