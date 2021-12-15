package com.aenlly.rcc.service;

import com.aenlly.rcc.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.List;

/**
 * 服务类
 *
 * @author aenlly
 * @since 2021-12-11
 */
public interface IUserService extends IService<User> {
  /**
   * 根据id查询用户信息
   *
   * @param id 用户id
   * @return 用户信息
   */
  @Override
  User getById(Serializable id);

  /**
   * 用户服务-积分排行
   *
   * @return 列表
   */
  List<User> getUserListByPoint();
}
