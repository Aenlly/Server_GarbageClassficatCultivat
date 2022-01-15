package com.aenlly.rcc.user.service;

import com.aenlly.rcc.entity.OrderUserView;
import com.aenlly.rcc.enums.OrderStateEnum;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * VIEW 服务类
 *
 * @author aenlly
 * @since 2021-12-15
 */
public interface IOrderUserViewService extends IService<OrderUserView> {

  /**
   * 根据用户编号查询内容
   *
   * @param userId 用户编号
   * @param state 核销状态
   * @return 订单列表
   */
  List<OrderUserView> getOrderUserList(String userId, OrderStateEnum state);
}
