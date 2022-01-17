package com.aenlly.rcc.admin.service;

import com.aenlly.rcc.entity.GiftType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 礼品类型表 服务类
 *
 * @author aenlly
 * @since 2022-01-17
 */
public interface IGiftTypeService extends IService<GiftType> {

  /**
   * 根据条件查询礼品类型信息
   *
   * @param text 类型名称
   * @return 信息集合
   */
  List<GiftType> getSelectListBy(String text);
}
