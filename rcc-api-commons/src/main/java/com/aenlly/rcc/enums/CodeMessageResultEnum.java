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
  ERROR(500, "请求失败！"),
  EXCEED(-1, "超出最大数量"),
  /** 已存在 */
  EXIST(300, "数据已存在"),
  /** 权限不足 */
  PERMISSION_NOT(403, "登录过期"),
  /** 积分不足 */
  NOT_POINT(400, "积分不足");

  private int code;

  private String message;
}
