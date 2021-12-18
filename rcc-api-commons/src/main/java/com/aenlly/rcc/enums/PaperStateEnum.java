package com.aenlly.rcc.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 答卷表提交状态
 *
 * @author Aenlly
 * @create by date 2021/12/18 13:44
 * @projectName RefuseClassificationCultivate
 */
@Getter
@AllArgsConstructor
public enum PaperStateEnum {
  /** 未提交，数据库插入0，默认值 */
  NOT_SUBMITTED(0, "未提交"),
  /** 已提交，数据库插入1 */
  SUBMITTED(1, "已提交");
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
