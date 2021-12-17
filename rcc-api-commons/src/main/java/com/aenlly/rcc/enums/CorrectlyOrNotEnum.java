package com.aenlly.rcc.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 答题结果(正确/错误)
 *
 * @author Aenlly
 * @create by date 2021/12/17 16:45
 * @projectName RefuseClassificationCultivate
 */
@Getter
@AllArgsConstructor
public enum CorrectlyOrNotEnum {
  /** 选项正确，数据库插入1 */
  CPRRECTLY("正确", 1),
  /** 选项错误，数据库插入-1 */
  NOT("错误", -1);
  /** 标记数据库中的值 */
  @JsonValue private String name;
  /** 返回的json显示此值 */
  @EnumValue private Integer value;
  /** 重写toString(),用于配合@JsonValue显示 */
  @Override
  public String toString() {
    return this.name;
  }
}
