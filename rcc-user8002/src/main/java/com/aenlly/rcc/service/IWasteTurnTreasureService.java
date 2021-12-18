package com.aenlly.rcc.service;

import com.aenlly.rcc.entity.WasteTurnTreasure;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 变废为宝表 服务类
 *
 * @author aenlly
 * @since 2021-12-18
 */
public interface IWasteTurnTreasureService extends IService<WasteTurnTreasure> {

  /**
   * 根据标签 获取变废为宝信息
   *
   * @param tag 标签
   * @return 信息集合
   */
  List<WasteTurnTreasure> getListByTag(Integer tag);
}
