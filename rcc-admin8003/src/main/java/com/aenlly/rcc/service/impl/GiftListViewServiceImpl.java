package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.service.IGiftListViewService;
import com.aenlly.rcc.entity.GiftListView;
import com.aenlly.rcc.mapper.GiftListViewMapper;
import com.aenlly.rcc.utils.enums.QueryGiftTypeEnum;
import com.aenlly.rcc.utils.wrapper.GiftWrapperUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 礼品信息表与礼品条目表 VIEW 服务实现类
 *
 * @author aenlly
 * @since 2022-01-17
 */
@Service
public class GiftListViewServiceImpl extends ServiceImpl<GiftListViewMapper, GiftListView>
    implements IGiftListViewService {

  /**
   * 查询信息集合
   *
   * @param page 分页对象
   * @param queryType 查询类型
   * @param text 查询内容
   * @param typeId 类型编号，可为null
   * @return 分页对象
   */
  @Override
  public IPage<GiftListView> getList(
      Page<GiftListView> page, QueryGiftTypeEnum queryType, String text, Long typeId) {
    Wrapper<GiftListView> wrapper = GiftWrapperUtil.queryListPage(queryType, text, typeId);
    return baseMapper.selectPage(page, wrapper);
  }
}
