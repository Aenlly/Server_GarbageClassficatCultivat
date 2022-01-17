package com.aenlly.rcc.admin.service.impl;

import com.aenlly.rcc.admin.service.IOrdersService;
import com.aenlly.rcc.entity.Orders;
import com.aenlly.rcc.enums.OrderStateEnum;
import com.aenlly.rcc.mapper.OrdersMapper;
import com.aenlly.rcc.utils.enums.QueryOrderTypeEnum;
import com.aenlly.rcc.utils.wrapper.OrdersWrapperUtil;
import com.aenlly.rcc.vo.OrderVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 订单记录表 服务实现类
 *
 * @author aenlly
 * @since 2022-01-16
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {
  /**
   * 查询信息集合
   *
   * @param page 分页对象
   * @param queryType 查询类型
   * @param text 查询内容
   * @param state 订单状态，可为null
   * @return 分页对象
   */
  @Override
  public IPage<OrderVo> getList(
      Page<OrderVo> page, QueryOrderTypeEnum queryType, String text, OrderStateEnum state) {
    Wrapper<OrderVo> wrapper = OrdersWrapperUtil.queryListPage(queryType, text, state);
    return baseMapper.getOrdersInfo(page, wrapper);
  }

  /**
   * 核销订单
   *
   * @param id 订单编号
   * @return 是否成功
   */
  @Override
  public Boolean writeOff(Long id) {
    Wrapper<Orders> wrapper = OrdersWrapperUtil.getWriteOff(id);
    return baseMapper.update(null, wrapper) > 0;
  }
}
