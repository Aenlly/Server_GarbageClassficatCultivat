package com.aenlly.rcc.service;

import com.aenlly.rcc.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 订单记录表 服务类
 *
 * @author aenlly
 * @since 2021-12-25
 */
public interface IOrdersService extends IService<Orders> {

  /**
   * 保存订单记录
   *
   * @param userId 用户编号
   * @param giftInfoId 礼品编号
   * @return 是否成功
   */
  Boolean save(String userId, Long giftInfoId);
}
