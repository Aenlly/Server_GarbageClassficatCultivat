package com.aenlly.rcc.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.aenlly.rcc.service.IAdminTableService;
import com.aenlly.rcc.entity.AdminTable;
import com.aenlly.rcc.entity.LoginAdmin;
import com.aenlly.rcc.enums.AdminLoginEnum;
import com.aenlly.rcc.mapper.AdminTableMapper;
import com.aenlly.rcc.utils.JWTUtil;
import com.aenlly.rcc.utils.wrapper.AdminWrapperUtil;
import com.aenlly.rcc.vo.LoginVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

  @Resource private AuthenticationManager authenticationManager;

  /**
   * 登录
   *
   * @param username 账号
   * @param password 密码
   * @return 基础信息
   */
  @Override
  public LoginVo adminLogin(String username, String password) {
    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(username, password);
    Authentication authenticate = authenticationManager.authenticate(authenticationToken);

    // 认证未通过
    if (ObjectUtil.isNull(authenticate)) {
      throw new NullPointerException();
    }

    LoginAdmin loginAdmin = (LoginAdmin) authenticate.getPrincipal();
    String json = JSONUtil.toJsonPrettyStr(loginAdmin.getAdminTable());

    String token = JWTUtil.createToken(json);

    return new LoginVo(
        token, loginAdmin.getAdminTable().getName(), loginAdmin.getAdminTable().getImgUrl());
  }

  /**
   * 修改密码
   *
   * @param id 管理员id
   * @param password 新密码
   * @return 是否成功
   */
  @Override
  public Boolean changePwd(Long id, String password) {
    String encode = passwordEncoder.encode(password);
    AdminTable entity = new AdminTable();
    entity.setPassword(encode);
    entity.setId(id);
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
