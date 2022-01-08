package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.entity.AdminTable;
import com.aenlly.rcc.enums.AdminLoginEnum;
import com.aenlly.rcc.mapper.AdminTableMapper;
import com.aenlly.rcc.service.IAdminTableService;
import com.aenlly.rcc.utils.wrapper.adminWrapperUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 管理员表 服务实现类
 *
 * @author aenlly
 * @since 2022-01-08
 */
@Service
public class AdminTableServiceImpl extends ServiceImpl<AdminTableMapper, AdminTable>
    implements IAdminTableService {

  /**
   * 登录
   *
   * @param username 账号
   * @param password 密码
   * @return 管理员信息
   */
  @Override
  public AdminTable adminLogin(String username, String password) {
    QueryWrapper<AdminTable> telWrapper =
        adminWrapperUtil.adminLogin(username, password, AdminLoginEnum.TEL);
    AdminTable adminTable = this.baseMapper.selectOne(telWrapper);
    if (adminTable == null) {
      QueryWrapper<AdminTable> emailWrapper =
          adminWrapperUtil.adminLogin(username, password, AdminLoginEnum.EMAIL);
      adminTable = this.baseMapper.selectOne(emailWrapper);
      if (adminTable == null) {
        throw new NullPointerException();
      }
    }
    return adminTable;
  }
}
