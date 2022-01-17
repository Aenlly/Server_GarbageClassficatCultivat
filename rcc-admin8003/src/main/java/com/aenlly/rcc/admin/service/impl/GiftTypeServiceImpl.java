package com.aenlly.rcc.admin.service.impl;

import com.aenlly.rcc.admin.service.IGiftTypeService;
import com.aenlly.rcc.entity.GiftType;
import com.aenlly.rcc.mapper.GiftTypeMapper;
import com.aenlly.rcc.utils.wrapper.GiftTypeWrapperUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 礼品类型表 服务实现类
 *
 * @author aenlly
 * @since 2022-01-17
 */
@Service
public class GiftTypeServiceImpl extends ServiceImpl<GiftTypeMapper, GiftType>
    implements IGiftTypeService {
  /**
   * 根据条件查询礼品类型信息
   *
   * @param text 类型名称
   * @return 信息集合
   */
  @Override
  public List<GiftType> getSelectListBy(String text) {
    Wrapper<GiftType> wrapper = GiftTypeWrapperUtil.queryByTypeName(text);
    return baseMapper.selectList(wrapper);
  }
}
