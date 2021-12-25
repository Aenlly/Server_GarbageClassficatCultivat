package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.entity.OrderUserView;
import com.aenlly.rcc.enums.OrderStateEnum;
import com.aenlly.rcc.mapper.OrderUserViewMapper;
import com.aenlly.rcc.service.IOrderUserViewService;
import com.aenlly.rcc.utils.wrapper.QueryWrapperUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * VIEW 服务实现类
 *
 * @author aenlly
 * @since 2021-12-15
 */
@Service
public class OrderUserViewServiceImpl extends ServiceImpl<OrderUserViewMapper, OrderUserView>
    implements IOrderUserViewService {

  /**
   * 根据用户编号查询内容
   *
   * @param userId 用户编号
   * @param state 核销状态
   * @return 订单列表
   */
  @Override
  public List<OrderUserView> getOrderUserList(String userId, OrderStateEnum state) {
    Wrapper<OrderUserView> wrapper = QueryWrapperUtil.getOrderUserList(userId, state);
    return baseMapper.selectList(wrapper);
  }
}
