package com.aenlly.rcc.service;

import com.aenlly.rcc.entity.GiftListView;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * VIEW 服务类
 *
 * @author aenlly
 * @since 2021-12-15
 */
public interface IGiftListViewService extends IService<GiftListView> {

  /**
   * 查询用户礼品兑换列表
   *
   * @param name 礼品名称
   * @param type 礼品类型
   * @return 礼品列表
   */
  List<GiftListView> getUserGiftList(String name, Integer type);
}
