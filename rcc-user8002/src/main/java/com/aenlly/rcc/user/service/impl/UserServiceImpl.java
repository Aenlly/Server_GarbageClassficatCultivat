package com.aenlly.rcc.user.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.aenlly.rcc.entity.LoginUser;
import com.aenlly.rcc.entity.User;
import com.aenlly.rcc.mapper.UserMapper;
import com.aenlly.rcc.user.service.IUserService;
import com.aenlly.rcc.utils.JWTUtil;
import com.aenlly.rcc.utils.wrapper.UserWrapperUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户信息表 服务实现类
 *
 * @author aenlly
 * @since 2021-12-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

  @Resource AuthenticationManager authenticationManager;

  /**
   * 根据id查询用户信息
   *
   * @param id 用户id
   * @return 用户信息
   */
  @Override
  public User getUserById(String id) {
    Wrapper<User> wrapper = UserWrapperUtil.getUserById(id);

    User user = this.getOne(wrapper);

    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(user.getUserId(), user.getUserId());
    Authentication authenticate = authenticationManager.authenticate(authenticationToken);
    // 认证未通过
    if (ObjectUtil.isNull(authenticate)) {
      throw new NullPointerException();
    }

    LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
    String json = JSONUtil.toJsonPrettyStr(loginUser);
    String token = JWTUtil.createToken(json);
    user.setUserId(token);
    return user;
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
    Wrapper<User> wrapper = UserWrapperUtil.getNameAndAvatarById(id);
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
