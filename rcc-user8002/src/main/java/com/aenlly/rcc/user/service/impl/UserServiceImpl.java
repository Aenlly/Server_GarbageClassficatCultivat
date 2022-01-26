package com.aenlly.rcc.user.service.impl;

import com.aenlly.rcc.entity.User;
import com.aenlly.rcc.mapper.UserMapper;
import com.aenlly.rcc.user.service.IUserService;
import com.aenlly.rcc.utils.wrapper.QueryWrapperUtil;
import com.aenlly.rcc.utils.wrapper.UserWrapperUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * 用户信息表 服务实现类
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
    Wrapper<User> wrapper = UserWrapperUtil.getUserById(id);
    return baseMapper.selectOne(wrapper);
  }

  /**
   * 用户服务-积分排行
   *
   * @return 列表
   */
  @Override
  public List<User> getUserListByPoint() {
    Wrapper<User> wrapper = UserWrapperUtil.getUserListByPoint();
    return baseMapper.selectList(wrapper);
  }

  /**
   * 用户服务-知识测验-答题积分排名
   *
   * @return 列表
   */
  @Override
  public List<User> getUserListByAnswerPoints() {
    Wrapper<User> wrapper = UserWrapperUtil.getUserListByAnswerPoints();
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

  /**
   * 剩余积分减少
   *
   * @param user 用户实体未改变信息时的对象
   * @param point 所减少的量
   * @return 是否成功
   */
  @Override
  @Transactional
  public Boolean updatePointReduce(User user, Long point) {
    user.setRemainingPoints(user.getRemainingPoints() - point);
    return baseMapper.updateById(user) > 0;
  }
}
