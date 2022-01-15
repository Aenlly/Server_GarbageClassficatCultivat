package com.aenlly.rcc.admin.service;

import com.aenlly.rcc.entity.AdminTable;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 管理员表 服务类
 *
 * @author aenlly
 * @since 2022-01-08
 */
public interface IAdminTableService extends IService<AdminTable> {
  /**
   * 登录
   *
   * @param username 账号
   * @param password 密码
   * @return 管理员信息
   */
  AdminTable adminLogin(String username, String password);
}
