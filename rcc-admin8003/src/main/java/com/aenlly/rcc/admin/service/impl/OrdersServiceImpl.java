package com.aenlly.rcc.admin.service.impl;

import com.aenlly.rcc.admin.service.IOrdersService;
import com.aenlly.rcc.entity.Orders;
import com.aenlly.rcc.mapper.OrdersMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 订单记录表 服务实现类
 *
 * @author aenlly
 * @since 2022-01-16
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders>
    implements IOrdersService {}
