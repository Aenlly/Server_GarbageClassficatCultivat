package com.aenlly.rcc.user.service;

import com.aenlly.rcc.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.List;

/**
 * 用户信息表 服务类
 *
 * @author aenlly
 * @since 2021-12-11
 */
public interface IUserService extends IService<User> {
  /**
   * 根据id查询用户信息
   *
   * @param id 用户id
   * @return 用户信息
   */
  @Override
  User getById(Serializable id);

  /**
   * 用户服务-积分排行
   *
   * @return 列表
   */
  List<User> getUserListByPoint();

  /**
   * 用户服务-知识测验-答题积分排名
   *
   * @return 列表
   */
  List<User> getUserListByAnswerPoints();

  /**
   * 根据用户编号 获取昵称与头像
   *
   * @param id 用户编号
   * @return 用户信息
   */
  User getNameAndAvatarById(String id);

  /**
   * 剩余积分减少
   *
   * @param user 用户实体未改变信息时的对象
   * @param point 所减少的量
   * @return 是否成功
   */
  Boolean updatePointReduce(User user, Integer point);
}
