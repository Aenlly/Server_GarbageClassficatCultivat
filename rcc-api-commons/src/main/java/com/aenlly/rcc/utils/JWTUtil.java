package com.aenlly.rcc.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * @author Aenlly
 * @create by date 2022/02/16 14:48
 * @projectName RefuseClassificationCultivate
 */
public class JWTUtil {

  /** 过期时间 */
  private static final Long JWT_TTL = 60 * 60 * 1000L;

  /** 密匙 */
  private static final String JWT_Key = "Aenlly";

  /**
   * 获取uuid
   *
   * @return uuid
   */
  private static String getUuid() {
    return UUID.randomUUID().toString().replace("-", "");
  }

  /**
   * 加密后的密匙
   *
   * @return 对称密匙
   */
  private static SecretKey getEncryptKey() {
    byte[] decode = Base64.getDecoder().decode(JWTUtil.JWT_Key);
    return new SecretKeySpec(decode, 0, decode.length, "AES");
  }

  /**
   * 获取jwt构建器
   *
   * @param subject 主题
   * @param ttlMillis 失效时间
   * @param uuid 唯一标识符
   * @return jwt构建器
   */
  private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
    SignatureAlgorithm algorithm = SignatureAlgorithm.HS512;
    SecretKey encryptKey = getEncryptKey();
    long timeMillis = System.currentTimeMillis();
    Date date = new Date(timeMillis);
    if (ttlMillis == null) {
      ttlMillis = JWTUtil.JWT_TTL;
    }
    long l = timeMillis + ttlMillis;
    Date expDate = new Date(l);
    return Jwts.builder()
        //	唯一标识符
        .setId(uuid)
        //	主题
        .setSubject(subject)
        //	作者
        .setIssuer("aenlly")
        //	提交时间
        .setIssuedAt(date)
        //	加密方法与密匙
        .signWith(algorithm, encryptKey)
        //	失效时间
        .setExpiration(expDate);
  }

  /**
   * @param subject 主题，可以使用json
   * @param ttlMillis 失效时间，默认1小时
   * @param id 唯一标识符
   * @return 加密后的token
   */
  public static String createToken(String subject, Long ttlMillis, String id) {
    JwtBuilder jwtBuilder = getJwtBuilder(subject, ttlMillis, id);
    return jwtBuilder.compact();
  }

  /**
   * @param subject 主题，可以使用json
   * @param ttlMillis 失效时间，默认1小时
   * @return 加密后的token
   */
  public static String createToken(String subject, Long ttlMillis) {
    JwtBuilder jwtBuilder = getJwtBuilder(subject, ttlMillis, getUuid());
    return jwtBuilder.compact();
  }

  /**
   * 加密
   *
   * @param subject 主题，可以使用json
   * @return 加密后的token
   */
  public static String createToken(String subject) {
    JwtBuilder jwtBuilder = getJwtBuilder(subject, null, getUuid());
    return jwtBuilder.compact();
  }

  /**
   * 解密
   *
   * @param token token
   * @return 解析后的内容
   */
  public static Claims parseJWT(String token) throws NullPointerException {
    return Jwts.parser().setSigningKey(getEncryptKey()).parseClaimsJws(token).getBody();
  }
}
