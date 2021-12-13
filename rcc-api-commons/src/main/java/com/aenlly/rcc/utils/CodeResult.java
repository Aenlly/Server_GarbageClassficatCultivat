package com.aenlly.rcc.utils;

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
public enum CodeResult {
  /** 成功 */
  OK(200),
  /** 失败 */
  ERROR(444);

  private int code;
}
