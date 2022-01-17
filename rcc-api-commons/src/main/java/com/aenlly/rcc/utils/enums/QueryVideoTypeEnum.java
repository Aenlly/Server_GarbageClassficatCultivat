package com.aenlly.rcc.utils.enums;

import com.aenlly.rcc.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Aenlly
 * @create by date 2022/01/11 16:36
 * @projectName RefuseClassificationCultivate
 */
@AllArgsConstructor
@Getter
public enum QueryVideoTypeEnum implements BaseEnum {
  /** 根据标题查询 */
  TITLE(0, "video_title"),
  /** 根据描述查询 */
  DESC(1, "video_desc");

  private Integer name;
  private String value;
}
