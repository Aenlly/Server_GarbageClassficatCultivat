package com.aenlly.rcc.service;

import com.aenlly.rcc.entity.PointsLog;
import com.aenlly.rcc.enums.PointsLogDescEnum;
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

  /**
   * 答题增加积分服务
   *
   * @param userId 用户编号
   * @param points 增加积分
   * @param pointsLogDescEnum 积分描述类型
   * @return 是否增加成功
   */
  boolean answerQuestion(String userId, Integer points, PointsLogDescEnum pointsLogDescEnum);

  /**
   * 礼品兑换日志
   *
   * @param userId 用户编号
   * @param points 消耗积分
   * @param desc 描述
   * @return 是否成功
   */
  boolean giftLog(String userId, Long points, String desc);
}
