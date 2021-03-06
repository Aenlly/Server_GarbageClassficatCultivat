package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.entity.GiftType;
import com.aenlly.rcc.mapper.GiftTypeMapper;
import com.aenlly.rcc.service.IGiftTypeService;
import com.aenlly.rcc.utils.wrapper.GiftTypeWrapperUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 礼品类型表 服务实现类
 *
 * @author aenlly
 * @since 2021-12-15
 */
@Service
public class GiftTypeServiceImpl extends ServiceImpl<GiftTypeMapper, GiftType>
    implements IGiftTypeService {

  /**
   * 用户礼品兑换展示类型内容查询
   *
   * @return 礼品类型
   */
  @Override
  public List<GiftType> getUserGiftTypeList() {
    Wrapper<GiftType> wrapper = GiftTypeWrapperUtil.getUserGiftTypeList();
    return baseMapper.selectList(wrapper);
  }
}
