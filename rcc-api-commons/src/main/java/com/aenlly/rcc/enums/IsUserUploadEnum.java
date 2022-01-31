package com.aenlly.rcc.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Aenlly
 * @create by date 2021/12/18 23:23
 * @projectName RefuseClassificationCultivate
 */
@Getter
@AllArgsConstructor
public enum IsUserUploadEnum implements BaseEnum {
  /** 是用户上传，数据库插入1 */
  YES(1, "是"),
  /** 非用户上传，数据库拆入0，默认值 */
  NO(0, "否");

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
