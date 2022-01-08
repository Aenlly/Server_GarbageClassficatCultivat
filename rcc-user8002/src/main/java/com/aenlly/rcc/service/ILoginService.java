package com.aenlly.rcc.service;

import com.aenlly.rcc.entity.User;

/**
 * @author Aenlly
 * @create by date 2022/01/08 19:49
 * @projectName RefuseClassificationCultivate
 */
public interface ILoginService {

  /**
   * 用户登录，不存在则创建
   *
   * @param code 用户临时登录凭证
   * @param nickName 用户昵称
   * @param avatarUrl 头像地址
   * @return 用户信息
   */
  User userLogin(String code, String nickName, String avatarUrl);
}
