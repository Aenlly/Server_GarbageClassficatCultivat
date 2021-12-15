package com.aenlly.rcc.service;

import com.aenlly.rcc.entity.Points;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 服务类
 *
 * @author aenlly
 * @since 2021-12-11
 */
public interface IPointsService extends IService<Points> {
  /**
   * 根据累积积分获得下一等级
   *
   * @param points 累积积分
   * @return 下一等级实体信息
   */
  Points getNextLevel(Integer points);
}
