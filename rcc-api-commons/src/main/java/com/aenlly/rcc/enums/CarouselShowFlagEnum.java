package com.aenlly.rcc.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 上线/下线状态枚举类
 *
 * @author Aenlly
 * @create by date 2022/01/14 16:40
 * @projectName RefuseClassificationCultivate
 */
@Getter
@AllArgsConstructor
public enum CarouselShowFlagEnum implements BaseEnum {

  /** 下线，数据库插入0 */
  OFFLINE(0, "下线"),
  /** 发布，数据库插入1 */
  PUBLISH(1, "发布");

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
