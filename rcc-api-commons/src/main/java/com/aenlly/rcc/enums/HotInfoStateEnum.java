package com.aenlly.rcc.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 热门资讯状态枚举类
 *
 * @author Aenlly
 * @create by date 2022/02/03 1:24
 * @projectName RefuseClassificationCultivate
 */
@Getter
@AllArgsConstructor
public enum HotInfoStateEnum implements BaseEnum {

  /** 已下线，数据库插入-1 */
  PUBLISH_NOT(-1, "已下线"),
  /** 待发布，数据库插入0 */
  PUBLISH_TO(0, "待发布"),
  /** 已发布，数据库插入1 */
  PUBLISH_OK(1, "已发布");

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
