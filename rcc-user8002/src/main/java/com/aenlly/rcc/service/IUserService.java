package com.aenlly.rcc.service;

import com.aenlly.rcc.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;

/**
 * 服务类
 *
 * @author aenlly
 * @since 2021-12-11
 */
public interface IUserService extends IService<User> {
  @Override
  User getById(Serializable id);
}
