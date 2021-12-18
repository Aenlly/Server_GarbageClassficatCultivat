package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.entity.User;
import com.aenlly.rcc.mapper.UserMapper;
import com.aenlly.rcc.service.IUserService;
import com.aenlly.rcc.utils.wrapper.QueryWrapperUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * 用户表 服务实现类
 *
 * @author aenlly
 * @since 2021-12-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
  /**
   * 根据id查询用户信息
   *
   * @param id 用户id
   * @return 用户信息
   */
  @Override
  public User getById(Serializable id) {
    Wrapper<User> wrapper = QueryWrapperUtil.getUserById(id);
    return baseMapper.selectOne(wrapper);
  }

  /**
   * 用户服务-积分排行
   *
   * @return 列表
   */
  @Override
  public List<User> getUserListByPoint() {
    Wrapper<User> wrapper = QueryWrapperUtil.getUserListByPoint();
    return baseMapper.selectList(wrapper);
  }

  /**
   * 用户服务-知识测验-答题积分排名
   *
   * @return 列表
   */
  @Override
  public List<User> getUserListByAnswerPoints() {
    Wrapper<User> wrapper = QueryWrapperUtil.getUserListByAnswerPoints();
    return baseMapper.selectList(wrapper);
  }

  /**
   * 根据用户编号 获取昵称与头像
   *
   * @param id 用户编号
   * @return 用户信息
   */
  @Override
  public User getNameAndAvatarById(String id) {
    Wrapper<User> wrapper = QueryWrapperUtil.getNameAndAvatarById(id);
    return baseMapper.selectOne(wrapper);
  }
}
