package com.aenlly.rcc.utils;

import cn.hutool.json.JSONUtil;
import com.aenlly.rcc.entity.LoginUser;
import com.aenlly.rcc.utils.JWTUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Aenlly
 * @create by date 2022/02/17 13:40
 * @projectName RefuseClassificationCultivate
 */
@Slf4j
public class TokenUtil {

  /**
   * token转userId
   *
   * @param token token
   * @return user_id
   */
  public static String toUserId(String token) {
    Claims claims = JWTUtil.parseJWT(token);
    String json = claims.getSubject();
    LoginUser loginUser = JSONUtil.toBean(json, LoginUser.class);
    return loginUser.getUserId();
  }
}
