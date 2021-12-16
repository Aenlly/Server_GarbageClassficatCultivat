package com.aenlly.rcc.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 微信小程序所需参数
 *
 * @author Aenlly
 * @create by date 2021/12/11 17:57
 * @projectName RefuseClassificationCultivate
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum WxParam {
  WX_URL(
      "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code"),
  /** 小程序 appId */
  APPID("wx3985a7db49aca5c0"),
  /** 小程序 appSecret */
  SECRET("dd93a5f46e9427a222cb14e5041bf2be");
  private String value;
}
