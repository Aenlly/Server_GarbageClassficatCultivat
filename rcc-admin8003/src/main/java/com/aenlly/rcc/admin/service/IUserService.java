package com.aenlly.rcc.admin.service;

import com.aenlly.rcc.entity.User;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 用户信息表 服务类
 *
 * @author aenlly
 * @since 2022-01-15
 */
public interface IUserService extends IService<User> {

  /**
   * 查询信息集合
   *
   * @param page 分页对象
   * @param text 查询内容
   * @return 分页对象
   */
  IPage<User> getList(Page<User> page, String text);
}
