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
    // 先按照手机号进行查询
    AdminTable adminTable = this.getOne(telWrapper);
    // 不存在则查邮箱
    if (adminTable == null) {
      // 按照邮箱进行查询
      QueryWrapper<AdminTable> emailWrapper =
          AdminWrapperUtil.adminLogin(username, AdminLoginEnum.EMAIL);
      adminTable = this.getOne(emailWrapper);
      // 邮箱不存在则抛出异常
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

  /**
   * 修改密码
   *
   * @param entity 管理员修改实体
   * @return 是否成功
   */
  @Override
  public Boolean changePwd(AdminTable entity) {
    String encode = passwordEncoder.encode(entity.getPassword());
    entity.setPassword(encode);
    return this.updateById(entity);
  }

  /**
   * 修改管理员信息
   *
   * @param entity 接收实体
   * @return 成功200，手机号重复100，邮箱重复300
   */
  @Override
  public Integer update(AdminTable entity) {
    QueryWrapper<AdminTable> telWrapper =
        AdminWrapperUtil.adminLogin(entity.getTel(), AdminLoginEnum.TEL);
    AdminTable selectOne = this.baseMapper.selectOne(telWrapper);
    if (selectOne != null && !selectOne.getId().equals(entity.getId())) {
      return 100;
    }
    QueryWrapper<AdminTable> emailWrapper =
        AdminWrapperUtil.adminLogin(entity.getEmail(), AdminLoginEnum.EMAIL);
    selectOne = this.baseMapper.selectOne(emailWrapper);
    if (selectOne != null && !selectOne.getId().equals(entity.getId())) {
      return 300;
    }
    boolean updateById = this.updateById(entity);
    if (updateById) {
      return 200;
    }
    throw new NullPointerException();
  }
}
