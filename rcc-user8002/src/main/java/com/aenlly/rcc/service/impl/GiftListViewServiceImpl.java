package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.entity.GiftListView;
import com.aenlly.rcc.mapper.GiftListViewMapper;
import com.aenlly.rcc.service.IGiftListViewService;
import com.aenlly.rcc.service.IGiftService;
import com.aenlly.rcc.utils.wrapper.GiftWrapperUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 VIEW 服务实现类

 @author aenlly
 @since 2021-12-15 */
@Service
public class GiftListViewServiceImpl extends ServiceImpl<GiftListViewMapper, GiftListView>
        implements IGiftListViewService {

  @Resource
  private IGiftService service;

  /**
   查询用户礼品兑换列表

   @param name 礼品名称
   @param type 礼品类型
   @return 礼品列表
   */
  @Override
  public List<GiftListView> getUserGiftList(String name, Integer type) {
    Wrapper<GiftListView> wrapper = GiftWrapperUtil.getUserGiftList(name, type);
    return baseMapper.selectList(wrapper);
  }

  /**
   根据 ID 查询

   @param id 主键ID
   */
  @Override
  public GiftListView getById(Serializable id) {
    Boolean result = service.increasePageviews(id);
    if (!result) {
      throw new NullPointerException();
    }
    return super.getById(id);
  }
}
