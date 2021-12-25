package com.aenlly.rcc.service;

import com.aenlly.rcc.entity.Gift;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 礼品信息表 服务类
 *
 * @author aenlly
 * @since 2021-12-25
 */
public interface IGiftService extends IService<Gift> {

  /**
   * 兑换服务
   *
   * <p>IndexOutOfBoundsException()异常代表积分不足
   *
   * <p>NullPointerException()异常代表部分过程错误
   *
   * @param userId 用户编号
   * @param id 礼品id
   * @return 兑换码
   */
  String convertById(String userId, Long id);
}
