package com.aenlly.rcc.admin.service.impl;

import com.aenlly.rcc.entity.AdminTable;
import com.aenlly.rcc.entity.LoginAdmin;
import com.aenlly.rcc.enums.AdminLoginEnum;
import com.aenlly.rcc.mapper.AdminTableMapper;
import com.aenlly.rcc.utils.wrapper.AdminWrapperUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Aenlly
 * @create by date 2022/02/16 13:27
 * @projectName RefuseClassificationCultivate
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

  @Resource AdminTableMapper mapper;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    QueryWrapper<AdminTable> telWrapper = AdminWrapperUtil.adminLogin(username, AdminLoginEnum.TEL);
    // 先按照手机号进行查询
    AdminTable adminTable = mapper.selectOne(telWrapper);
    // 不存在则查邮箱
    if (adminTable == null) {
      // 按照邮箱进行查询
      QueryWrapper<AdminTable> emailWrapper =
          AdminWrapperUtil.adminLogin(username, AdminLoginEnum.EMAIL);
      adminTable = mapper.selectOne(emailWrapper);
      // 邮箱不存在则抛出异常
      if (adminTable == null) {
        throw new NullPointerException();
      }
    }

    return new LoginAdmin(adminTable);
  }
}
