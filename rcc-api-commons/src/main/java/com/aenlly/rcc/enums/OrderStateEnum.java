package com.aenlly.rcc.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单状态枚举类
 *
 * @author Aenlly
 * @create by date 2021/12/25 22:48
 * @projectName RefuseClassificationCultivate
 */
@Getter
@AllArgsConstructor
public enum OrderStateEnum implements BaseEnum {
  /** 已核销，数据库插入1 */
  WRITEN_OFF(1, "已核销"),
  /** 未核销，数据库插入-1,默认值 */
  NOT_WRITEN_OFF(-1, "未核销");

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
