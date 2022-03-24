package com.aenlly.rcc.service;

import com.aenlly.rcc.entity.AdminTable;
import com.aenlly.rcc.vo.LoginVo;
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
   * @return 基础信息
   */
  LoginVo adminLogin(String username, String password);

  /**
   * 修改密码
   *
   * @param id 管理员id
   * @param password 新密码
   * @return 是否成功
   */
  Boolean changePwd(Long id, String password);

  /**
   * 修改管理员信息
   *
   * @param entity 接收实体
   * @return 成功200，手机号重复100，邮箱重复300
   */
  Integer update(AdminTable entity);
}
