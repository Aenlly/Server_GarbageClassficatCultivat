package com.aenlly.rcc.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 搜索类型枚举映射类
 *
 * @author Aenlly
 * @create by date 2021/12/14 21:51
 * @projectName RefuseClassificationCultivate
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum SearchTypeEnum implements BaseEnum {
  /** 文字搜索，数据库插入0 */
  SEARCH_TEXT(0, "文字搜索"),
  /** 语音搜索，数据库插入1 */
  SEARCH_VOICE(1, "语音搜索"),
  /** 图片搜索，数据库插入2 */
  SEARCH_PICTURE(2, "图片识别");

  /** 标记数据库中的值 */
  @EnumValue private Integer name;

  /** 返回的json显示此值 */
  @JsonValue private String value;

  /** 重写toString(),用于配合@JsonValue显示 */
  @Override
  public String toString() {
    return this.value;
  }
}
