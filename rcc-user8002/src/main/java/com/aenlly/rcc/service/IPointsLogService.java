package com.aenlly.rcc.service;

import com.aenlly.rcc.entity.PointsLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 服务类
 *
 * @author aenlly
 * @since 2021-12-16
 */
public interface IPointsLogService extends IService<PointsLog> {

  /**
   * 根据用户编号-积分记录类型查询
   *
   * @param userId 用户编号
   * @param type 积分记录类型
   * @return 积分记录列表
   */
  List<PointsLog> getPointsLogByUserIdList(String userId, Integer type);

  /**
   * 每日签到服务
   *
   * @param userId 用户编号
   * @return 是否签到成功
   */
  boolean dailyCheck(String userId);
}
