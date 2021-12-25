package com.aenlly.rcc.utils.wrapper;

import com.aenlly.rcc.entity.GiftInfo;
import com.aenlly.rcc.enums.GiftStateEnum;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * 礼品数据操作对象类
 *
 * @author Aenlly
 * @create by date 2021/12/25 21:51
 * @projectName RefuseClassificationCultivate
 */
public class GiftWrapperUtil {

  /**
   * 根据礼品id查询未售出的详细信息 获得操作对象
   *
   * @param giftId 礼品id
   * @return 查询对象
   */
  public static Wrapper<GiftInfo> getGiftInfoByNotSold(Long giftId) {
    QueryWrapper<GiftInfo> wrapper = new QueryWrapper<>();
    wrapper
        .select("id")
        .eq("gift_id", giftId)
        .eq("state", GiftStateEnum.NOT_SOLD.getName())
        .last("limit 1");
    return wrapper;
  }
}
