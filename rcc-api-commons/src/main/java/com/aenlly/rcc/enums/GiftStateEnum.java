package com.aenlly.rcc.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 礼品信息状态
 *
 * @author Aenlly
 * @create by date 2021/12/25 21:56
 * @projectName RefuseClassificationCultivate
 */
@Getter
@AllArgsConstructor
public enum GiftStateEnum implements BaseEnum {
  /** 未售出，数据库插入1，默认值 */
  NOT_SOLD(1, "未售出"),
  /** 售出，数据库插入0 */
  SOLD(0, "售出");

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
