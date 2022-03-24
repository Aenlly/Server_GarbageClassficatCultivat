package com.aenlly.rcc.service;

import com.aenlly.rcc.entity.GiftListView;
import com.aenlly.rcc.utils.enums.QueryGiftTypeEnum;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 礼品信息表与礼品条目表 VIEW 服务类
 *
 * @author aenlly
 * @since 2022-01-17
 */
public interface IGiftListViewService extends IService<GiftListView> {

  /**
   * 查询信息集合
   *
   * @param page 分页对象
   * @param queryType 查询类型
   * @param text 查询内容
   * @param typeId 类型编号，可为null
   * @return 分页对象
   */
  IPage<GiftListView> getList(
      Page<GiftListView> page, QueryGiftTypeEnum queryType, String text, Long typeId);
}
