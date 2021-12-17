package com.aenlly.rcc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 返回状态码
 *
 * @author Aenlly
 * @create by date 2021/12/11 15:58
 * @projectName RefuseClassificationCultivate
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum CodeMessageResultEnum {
  /** 成功 */
  OK(200, "请求成功！"),
  /** 失败 */
  ERROR(444, "请求失败！"),
  /** 已存在 */
  EXIST(300, "数据已存在");

  private int code;

  private String message;
}
