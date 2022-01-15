package com.aenlly.rcc.admin.service.impl;

import com.aenlly.rcc.admin.service.IUserService;
import com.aenlly.rcc.entity.User;
import com.aenlly.rcc.mapper.UserMapper;
import com.aenlly.rcc.utils.wrapper.UserWrapperUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用户信息表 服务实现类
 *
 * @author aenlly
 * @since 2022-01-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

  /**
   * 查询信息集合
   *
   * @param page 分页对象
   * @param text 查询内容
   * @return 分页对象
   */
  @Override
  public IPage<User> getList(Page<User> page, String text) {
    Wrapper<User> wrapper = UserWrapperUtil.queryListPage(text);
    return baseMapper.selectPage(page, wrapper);
  }
}
