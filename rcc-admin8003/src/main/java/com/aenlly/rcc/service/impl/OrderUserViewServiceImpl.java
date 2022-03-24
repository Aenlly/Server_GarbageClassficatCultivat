package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.service.IOrderUserViewService;
import com.aenlly.rcc.entity.OrderUserView;
import com.aenlly.rcc.mapper.OrderUserViewMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * VIEW 服务实现类
 *
 * @author aenlly
 * @since 2021-12-15
 */
@Service
public class OrderUserViewServiceImpl extends ServiceImpl<OrderUserViewMapper, OrderUserView>
    implements IOrderUserViewService {}
