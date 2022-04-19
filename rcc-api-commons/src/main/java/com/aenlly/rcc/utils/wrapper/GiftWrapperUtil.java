package com.aenlly.rcc.utils.wrapper;

import com.aenlly.rcc.entity.Gift;
import com.aenlly.rcc.entity.GiftInfo;
import com.aenlly.rcc.entity.GiftListView;
import com.aenlly.rcc.enums.GiftStateEnum;
import com.aenlly.rcc.utils.enums.QueryGiftTypeEnum;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;

import java.io.Serializable;

/**
 * 礼品信息表实体封装操作对象获取工具类
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

  /**
   * 根据查询依据条件与查询条件内容，以及礼品类型编号，获取操作对象
   *
   * @param queryType 查询依据条件
   * @param text 查询内容
   * @param typeId 礼品类型编号，可为null
   */
  public static Wrapper<GiftListView> queryListPage(
      QueryGiftTypeEnum queryType, String text, Long typeId) {
    QueryWrapper<GiftListView> wrapper = new QueryWrapper<>();
    wrapper.like(queryType.getValue(), text);
    if (typeId != null) {
      wrapper.eq("type_id", typeId);
    }
    return wrapper;
  }

  public static Wrapper<GiftInfo> delByGiftId(Long id, Long number) {
    QueryWrapper<GiftInfo> wrapper = new QueryWrapper<>();
    wrapper.select("id").eq("gift_id", id).eq("state", 1).last("limit " + number);
    return wrapper;
  }

  /**
   * 根据礼品名称、类型获得操作对象
   *
   * @param name 礼品名称
   * @param type 礼品类型
   * @return 查询对象
   */
  public static Wrapper<GiftListView> getUserGiftList(String name, Integer type) {
    QueryWrapper<GiftListView> wrapper = new QueryWrapper<>();
    wrapper.select("gift_id", "gift_name", "img_url", "point", "price")
           .like("gift_name", name);
    if (type != -1) {
      wrapper.eq("type_id", type);
    }
    return wrapper;
  }

  /**
   根据id，创建增加浏览量操作对象

   @param id 礼品编号
   @return 操作对象
   */
  public static Wrapper<Gift> increasePageviews(Serializable id) {
    UpdateWrapper<Gift> wrapper = new UpdateWrapper<>();
    wrapper.eq("gift_id", id)
           .setSql(true, "browse_count=browse_count+1");
    return wrapper;
  }

}
