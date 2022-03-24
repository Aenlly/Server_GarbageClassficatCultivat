package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.entity.Orders;
import com.aenlly.rcc.mapper.OrdersMapper;
import com.aenlly.rcc.service.IOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 订单记录表 服务实现类
 *
 * @author aenlly
 * @since 2021-12-25
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {

  /** 订单对象 */
  @Resource private Orders orders;

  /**
   * 保存订单记录
   *
   * @param userId 用户编号
   * @param giftInfoId 礼品编号
   * @return 是否成功
   */
  @Override
  @Transactional
  public Boolean save(String userId, Long giftInfoId) {
    orders.setOrderId(null);
    orders.setUserId(userId);
    orders.setGiftInfoId(giftInfoId);
    return baseMapper.insert(orders) > 0;
  }
}
