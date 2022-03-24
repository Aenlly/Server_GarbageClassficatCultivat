package com.aenlly.rcc.service;

import com.aenlly.rcc.entity.OrderUserView;
import com.aenlly.rcc.entity.PointsLog;
import com.aenlly.rcc.entity.User;
import com.aenlly.rcc.utils.enums.QueryPointsTypeEnum;
import com.aenlly.rcc.vo.UserVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 用户信息表 服务类
 *
 * @author aenlly
 * @since 2022-01-15
 */
public interface IUserService extends IService<User> {

  /**
   * 查询信息集合
   *
   * @param page 分页对象
   * @param text 查询内容
   * @param typeEnum 排序条件，可省略
   * @return 分页对象
   */
  IPage<UserVo> getList(Page<UserVo> page, String text, QueryPointsTypeEnum typeEnum);

  /**
   * 查询订单记录信息集合
   *
   * @param page 分页对象
   * @param userId 户id
   * @return 分页对象
   */
  IPage<OrderUserView> getOrderListById(Page<OrderUserView> page, String userId);

  /**
   * 查询积分记录信息集合
   *
   * @param page 分页对象
   * @param userId 用户id
   * @return 分页对象
   */
  IPage<PointsLog> getPointsListById(Page<PointsLog> page, String userId);
}
