package com.aenlly.rcc.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Aenlly
 * @create by date 2021/12/17 21:22
 * @projectName RefuseClassificationCultivate
 */
@Getter
@AllArgsConstructor
public enum TopicStateEnum {
  /** 未答，数据库插入0，默认值 */
  UN_ANSWERED(0, "未答"),
  /** 已答，数据库插入1 */
  ANSWERED(1, "已答");
  /** 标记数据库中的值 */
  @EnumValue private Integer state;
  /** 返回的json显示此值 */
  @JsonValue private String value;
  /** 重写toString(),用于配合@JsonValue显示 */
  @Override
  public String toString() {
    return this.value;
  }
}
