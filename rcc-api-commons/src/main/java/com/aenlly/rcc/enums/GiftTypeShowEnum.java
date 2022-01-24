package com.aenlly.rcc.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 礼品类型显示状态
 *
 * @author Aenlly
 * @create by date 2022/01/18 15:25
 * @projectName RefuseClassificationCultivate
 */
@Getter
@AllArgsConstructor
public enum GiftTypeShowEnum implements BaseEnum {

  /** 显示在用户礼品兑换，数据库插入1，默认值 */
  DISPLAY(1, "是"),
  /** 不显示在用户礼品兑换，数据库插入0 */
  NOT_DISPLAY(0, "否");

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
