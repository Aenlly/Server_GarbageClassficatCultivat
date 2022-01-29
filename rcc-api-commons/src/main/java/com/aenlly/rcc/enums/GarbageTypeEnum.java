package com.aenlly.rcc.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 垃圾类型映射类
 *
 * @author Aenlly
 * @create by date 2022/01/30 0:44
 * @projectName RefuseClassificationCultivate
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum GarbageTypeEnum implements BaseEnum {
  /** 可回收垃圾，数据库插入0，默认值 */
  RECYCLABLE(0, "可回收垃圾"),
  /** 厨余垃圾，数据库插入2 */
  KITCHEN(2, "厨余垃圾"),
  /** 有害垃圾，数据库插入1 */
  HARMFUL(1, "有害垃圾"),
  /** 其他垃圾，数据库插入3 */
  OTHER(3, "其他垃圾");

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
