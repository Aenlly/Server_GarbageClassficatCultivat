package com.aenlly.rcc.mapper;

import com.aenlly.rcc.entity.Orders;
import com.aenlly.rcc.vo.OrderVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 订单记录表 Mapper 接口
 *
 * @author aenlly
 * @since 2021-12-25
 */
public interface OrdersMapper extends BaseMapper<Orders> {

  /**
   * 多表联查，订单表、礼品条目表、礼品表
   *
   * <p>${ew.customSqlSegment}让wrapper对象条件拼接
   *
   * @param page 分页对象
   * @param wrapper 实体封装操作条件
   * @return 查询内容
   */
  @Select(
      "select o.order_id,gi.gift_id,o.user_id,g.gift_name,gi.code,o.state,o.create_time from orders o left join "
          + "gift_info gi on o"
          + ".gift_info_id=gi.id left join gift g  on gi.gift_id=g.gift_id  ${ew.customSqlSegment}")
  IPage<OrderVo> getOrdersInfo(
      Page<OrderVo> page, @Param(Constants.WRAPPER) Wrapper<OrderVo> wrapper);
}
