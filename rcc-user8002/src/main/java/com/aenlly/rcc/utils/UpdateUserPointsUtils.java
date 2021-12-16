package com.aenlly.rcc.utils;

import com.aenlly.rcc.entity.Points;
import com.aenlly.rcc.entity.PointsLog;
import com.aenlly.rcc.entity.User;
import com.aenlly.rcc.enums.PointsLogDescEnum;
import com.aenlly.rcc.enums.PointsLogTypeEnum;
import com.aenlly.rcc.service.IPointsLogService;
import com.aenlly.rcc.service.IPointsService;
import com.aenlly.rcc.service.IUserService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 更新用户数据工具类
 *
 * @author Aenlly
 * @create by date 2021/12/16 21:16
 * @projectName RefuseClassificationCultivate
 */
@Component
public class UpdateUserPointsUtils {

  @Resource PointsLog pointsLog;
  @Resource IUserService userService;
  @Resource IPointsLogService pointsLogService;

  @Resource IPointsService pointsService;

  @Transactional
  public Boolean dailyCheck(String userId) {
    pointsLog.setId(null);
    pointsLog.setLogDesc(PointsLogDescEnum.DAILY_CHECK.getValue());
    pointsLog.setNumber(PointsLogDescEnum.DAILY_CHECK.getPoints());
    pointsLog.setUserId(userId);
    pointsLog.setType(PointsLogTypeEnum.ADD);
    boolean b = pointsLogService.save(pointsLog);
    if (b) {
      return updateUser(userId, PointsLogDescEnum.DAILY_CHECK.getPoints());
    }
    throw new NullPointerException();
  }

  private Boolean updateUser(String userId, Integer points) {
    User user = userService.getById(userId);
    // 剩余积分
    user.setRemainingPoints(user.getRemainingPoints() + points);
    // 累积积分
    user.setAccumulativePoints(user.getAccumulativePoints() + points);

    // 查询下一等级
    Points level = pointsService.getCurrentLevel(user.getAccumulativePoints());
    // 不为空时设置下一等级
    if (level != null) {
      user.setPointsId(level.getPointsId());
    }
    return userService.updateById(user);
  }
}
