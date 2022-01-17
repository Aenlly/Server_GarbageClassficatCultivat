package com.aenlly.rcc.utils.wrapper;

import com.aenlly.rcc.entity.Orders;
import com.aenlly.rcc.enums.OrderStateEnum;
import com.aenlly.rcc.utils.enums.QueryOrderTypeEnum;
import com.aenlly.rcc.vo.OrderVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;

/**
 * 订单信息实体表封装操作对象获取根据类
 *
 * @author Aenlly
 * @create by date 2022/01/17 16:38
 * @projectName RefuseClassificationCultivate
 */
public class OrdersWrapperUtil {

  /**
   * 根据条件获取实体操作对象
   *
   * @param queryType 查询条件
   * @param text 查询内容
   * @param state 订单状态条件，可为null
   * @return 查询对象
   */
  public static Wrapper<OrderVo> queryListPage(
      QueryOrderTypeEnum queryType, String text, OrderStateEnum state) {
    QueryWrapper<OrderVo> wrapper = new QueryWrapper<>();
    wrapper.like(queryType.getValue(), text);
    if (state != null) {
      wrapper.eq("o.state", state);
    }
    return wrapper;
  }

  /**
   * 根据订单编号核销订单的获取实体封装操作对象
   *
   * @param id 用户编号
   * @return 更新对象
   */
  public static Wrapper<Orders> getWriteOff(Long id) {
    UpdateWrapper<Orders> wrapper = new UpdateWrapper<>();
    wrapper.set("state", OrderStateEnum.WRITEN_OFF).eq("order_id", id);
    return wrapper;
  }
}
