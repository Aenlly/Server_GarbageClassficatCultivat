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
public enum CorrectlyOrNotEnum implements BaseEnum {
  /** 选项正确，数据库插入1 */
  CPRRECTLY(1, "正确"),
  /** 选项错误，数据库插入-1 */
  NOT(-1, "错误");
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
