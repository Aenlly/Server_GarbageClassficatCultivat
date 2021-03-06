package com.aenlly.rcc.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 视频审核状态
 *
 * @author Aenlly
 * @create by date 2021/12/18 20:54
 * @projectName RefuseClassificationCultivate
 */
@Getter
@AllArgsConstructor
public enum AuditEnum implements BaseEnum {
  /** 未通过，数据库插入-1 */
  Not_THROUGH(-1, "未通过"),
  /** 待审核，数据库插入0 */
  TO_AUDIT(0, "待审核"),
  /** 已发布，数据库插入1 */
  THROUGH(1, "已发布"),
  /** 已下架，数据库插入3 */
  OFF(2, "已下架");

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
