package com.aenlly.rcc.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 积分记录类型
 *
 * @author Aenlly
 * @create by date 2021/12/16 13:55
 * @projectName RefuseClassificationCultivate
 */
@AllArgsConstructor
@Getter
public enum PointsLogTypeEnum {
  /** 增加，数据库插入1 */
  ADD(1, "增加"),
  /** 减少，数据库插入-1 */
  LOWER(-1, "减少");

  /** 标记数据库中的值 */
  @EnumValue private Integer type;

  /** 返回的json显示此值 */
  @JsonValue private String value;

  /** 重写toString(),用于配合@JsonValue显示 */
  @Override
  public String toString() {
    return this.value;
  }
}
