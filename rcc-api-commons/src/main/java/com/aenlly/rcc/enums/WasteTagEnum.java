package com.aenlly.rcc.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 变废为宝表视频类型
 *
 * @author Aenlly
 * @create by date 2021/12/18 20:54
 * @projectName RefuseClassificationCultivate
 */
@Getter
@AllArgsConstructor
public enum WasteTagEnum implements BaseEnum {
  /** 好用，数据库插入0 */
  HANDY(0, "好用"),
  /** 好看，数据库插入1 */
  NICE(1, "好看"),
  /** 好玩，数据库插入2 */
  FUN(2, "好玩");

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
