package com.aenlly.rcc.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 返回信息
 *
 * @author Aenlly
 * @create by date 2021/12/11 16:05
 * @projectName RefuseClassificationCultivate
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum MessageResult {
  OK("请求成功"),
  ERROR("请求失败");

  private String msg;
}
