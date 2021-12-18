package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.entity.PointsLog;
import com.aenlly.rcc.enums.PointsLogDescEnum;
import com.aenlly.rcc.mapper.PointsLogMapper;
import com.aenlly.rcc.service.IPointsLogService;
import com.aenlly.rcc.utils.UpdateUserPointsUtils;
import com.aenlly.rcc.utils.wrapper.QueryWrapperUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 服务实现类
 *
 * @author aenlly
 * @since 2021-12-16
 */
@Service
public class PointsLogServiceImpl extends ServiceImpl<PointsLogMapper, PointsLog>
    implements IPointsLogService {

  @Resource UpdateUserPointsUtils userPointsUtils;

  /**
   * 根据用户编号-积分记录类型查询
   *
   * @param userId 用户编号
   * @param type 积分记录类型
   * @return 积分记录列表
   */
  @Override
  public List<PointsLog> getPointsLogByUserIdList(String userId, Integer type) {
    Wrapper<PointsLog> wrapper = QueryWrapperUtil.getPointsLogBy(userId, type);
    return baseMapper.selectList(wrapper);
  }

  /**
   * 每日签到服务
   *
   * @param userId 用户编号
   * @return 是否签到成功
   */
  @Override
  @Transactional // 事务回滚
  public boolean dailyCheck(String userId) {
    Wrapper<PointsLog> wrapper = QueryWrapperUtil.isDailyCheck(userId);
    // 判断是否已签到
    Long count = baseMapper.selectCount(wrapper);
    if (count == 0) {
      return userPointsUtils.dailyCheck(userId);
    }
    return false;
  }

  /**
   * 答题增加积分服务
   *
   * @param userId 用户编号
   * @param points 增加积分
   * @param pointsLogDescEnum 积分描述类型
   * @return 是否增加成功
   */
  @Override
  public boolean answerQuestion(
      String userId, Integer points, PointsLogDescEnum pointsLogDescEnum) {
    return userPointsUtils.answerQuestion(userId, points, pointsLogDescEnum);
  }
}
