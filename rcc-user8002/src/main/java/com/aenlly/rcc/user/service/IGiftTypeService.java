package com.aenlly.rcc.user.service;

import com.aenlly.rcc.entity.GiftType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 服务类
 *
 * @author aenlly
 * @since 2021-12-15
 */
public interface IGiftTypeService extends IService<GiftType> {

  /**
   * 用户礼品兑换展示类型内容查询
   *
   * @return 礼品类型
   */
  List<GiftType> getUserGiftTypeList();
}
