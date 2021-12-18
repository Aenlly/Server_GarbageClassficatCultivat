package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.entity.WasteTurnTreasure;
import com.aenlly.rcc.mapper.WasteTurnTreasureMapper;
import com.aenlly.rcc.service.IWasteTurnTreasureService;
import com.aenlly.rcc.utils.wrapper.WasteWrapperUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 变废为宝表 服务实现类
 *
 * @author aenlly
 * @since 2021-12-18
 */
@Service
public class WasteTurnTreasureServiceImpl
    extends ServiceImpl<WasteTurnTreasureMapper, WasteTurnTreasure>
    implements IWasteTurnTreasureService {

  /**
   * 根据标签 获取变废为宝信息
   *
   * @param tag 标签
   * @return 信息集合
   */
  @Override
  public List<WasteTurnTreasure> getListByTag(Integer tag) {
    Wrapper<WasteTurnTreasure> wrapper = WasteWrapperUtil.getListByTag(tag);
    return baseMapper.selectList(wrapper);
  }

  /**
   * 根据标题 获取变废为宝信息
   *
   * @param title 标题
   * @return 信息集合
   */
  @Override
  public List<WasteTurnTreasure> getListSearchByTitle(String title) {
    Wrapper<WasteTurnTreasure> wrapper = WasteWrapperUtil.getListByTitle(title);
    return baseMapper.selectList(wrapper);
  }
}
