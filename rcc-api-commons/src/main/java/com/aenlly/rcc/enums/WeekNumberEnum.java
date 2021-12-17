package com.aenlly.rcc.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 每周可答次数
 *
 * @author Aenlly
 * @create by date 2021/12/17 21:46
 * @projectName RefuseClassificationCultivate
 */
@Getter
@AllArgsConstructor
public enum WeekNumberEnum {
  /** 每天一次，数据库插入7 */
  DAILY(7, "每天一次"),
  /** 每周一次，数据库插入1，默认值 */
  WEEK(1, "每周一次");

  /** 标记数据库中的值 */
  @EnumValue private Integer weekNumber;
  /** 返回的json显示此值 */
  @JsonValue private String value;
  /** 重写toString(),用于配合@JsonValue显示 */
  @Override
  public String toString() {
    return this.value;
  }
}
