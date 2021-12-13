package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.entity.User;
import com.aenlly.rcc.mapper.UserMapper;
import com.aenlly.rcc.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * 服务实现类
 *
 * @author aenlly
 * @since 2021-12-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
  @Override
  public User getById(Serializable id) {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper
        .select(
            "user_id",
            "nick_name",
            "avatar_url",
            "accumulative_points",
            "remaining_points",
            "answer_points",
            "points_id")
        .eq("user_id", id);
    return baseMapper.selectOne(queryWrapper);
  }
}
