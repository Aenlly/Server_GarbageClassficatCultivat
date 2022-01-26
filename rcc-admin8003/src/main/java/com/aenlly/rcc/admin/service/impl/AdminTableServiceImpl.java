package com.aenlly.rcc.admin.service.impl;

import com.aenlly.rcc.admin.service.IAdminTableService;
import com.aenlly.rcc.entity.AdminTable;
import com.aenlly.rcc.enums.AdminLoginEnum;
import com.aenlly.rcc.mapper.AdminTableMapper;
import com.aenlly.rcc.utils.wrapper.AdminWrapperUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 管理员表 服务实现类
 *
 * @author aenlly
 * @since 2022-01-08
 */
@Service
public class AdminTableServiceImpl extends ServiceImpl<AdminTableMapper, AdminTable>
    implements IAdminTableService {

  @Resource private PasswordEncoder passwordEncoder;

  /**
   * 登录
   *
   * @param username 账号
   * @param password 密码
   * @return 管理员信息
   */
  @Override
  public AdminTable adminLogin(String username, String password) {
    QueryWrapper<AdminTable> telWrapper = AdminWrapperUtil.adminLogin(username, AdminLoginEnum.TEL);
    AdminTable adminTable = this.baseMapper.selectOne(telWrapper);
    if (adminTable == null) {
      QueryWrapper<AdminTable> emailWrapper =
          AdminWrapperUtil.adminLogin(username, AdminLoginEnum.EMAIL);
      adminTable = this.baseMapper.selectOne(emailWrapper);
      if (adminTable == null) {
        throw new NullPointerException();
      }
    }
    // 与加密后的密码进行比对
    boolean matches = passwordEncoder.matches(password, adminTable.getPassword());
    String encode = passwordEncoder.encode(adminTable.getPassword());
    adminTable.setToken(encode);
    adminTable.setPassword(null);
    if (!matches) {
      throw new NullPointerException();
    }
    return adminTable;
  }
}
