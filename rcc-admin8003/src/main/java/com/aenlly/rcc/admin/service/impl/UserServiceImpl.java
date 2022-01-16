package com.aenlly.rcc.admin.service.impl;

import com.aenlly.rcc.admin.service.IOrderUserViewService;
import com.aenlly.rcc.admin.service.IPointsLogService;
import com.aenlly.rcc.admin.service.IUserService;
import com.aenlly.rcc.entity.OrderUserView;
import com.aenlly.rcc.entity.PointsLog;
import com.aenlly.rcc.entity.User;
import com.aenlly.rcc.mapper.UserMapper;
import com.aenlly.rcc.utils.wrapper.UserWrapperUtil;
import com.aenlly.rcc.vo.UserVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户信息表 服务实现类
 *
 * @author aenlly
 * @since 2022-01-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

  /** 订单记录表服务对象 */
  @Resource private IOrderUserViewService orderUserViewService;
  /** 积分记录表服务对象 */
  @Resource private IPointsLogService pointsLogService;

  /**
   * 查询信息集合
   *
   * @param page 分页对象
   * @param text 查询内容
   * @return 分页对象
   */
  @Override
  public IPage<UserVo> getList(Page<UserVo> page, String text) {
    Wrapper<UserVo> wrapper = UserWrapperUtil.queryListPage(text);
    return baseMapper.getUserPoints(page, wrapper);
  }

  /**
   * 查询订单记录信息集合
   *
   * @param page 分页对象
   * @param userId 户id
   * @return 分页对象
   */
  @Override
  public IPage<OrderUserView> getOrderListById(Page<OrderUserView> page, String userId) {
    Wrapper<OrderUserView> wrapper = UserWrapperUtil.getOrderListById(userId);
    return orderUserViewService.page(page, wrapper);
  }

  /**
   * 查询积分记录信息集合
   *
   * @param page 分页对象
   * @param userId 用户id
   * @return 分页对象
   */
  @Override
  public IPage<PointsLog> getPointsListById(Page<PointsLog> page, String userId) {
    Wrapper<PointsLog> wrapper = UserWrapperUtil.getPointsListById(userId);
    return pointsLogService.page(page, wrapper);
  }
}
