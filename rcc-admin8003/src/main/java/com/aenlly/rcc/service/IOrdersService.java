package com.aenlly.rcc.service;

import com.aenlly.rcc.entity.Orders;
import com.aenlly.rcc.enums.OrderStateEnum;
import com.aenlly.rcc.utils.enums.QueryOrderTypeEnum;
import com.aenlly.rcc.vo.OrderVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 订单记录表 服务类
 *
 * @author aenlly
 * @since 2022-01-16
 */
public interface IOrdersService extends IService<Orders> {

  /**
   * 查询信息集合
   *
   * @param page 分页对象
   * @param queryType 查询类型
   * @param text 查询内容
   * @param state 订单状态，可为null
   * @return 分页对象
   */
  IPage<OrderVo> getList(
      Page<OrderVo> page, QueryOrderTypeEnum queryType, String text, OrderStateEnum state);

  /**
   * 核销订单
   *
   * @param id 订单编号
   * @return 是否成功
   */
  Boolean writeOff(Long id);
}
