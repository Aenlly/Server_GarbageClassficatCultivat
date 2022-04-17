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
import org.springframework.transaction.annotation.Propagation;
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

  /** 用户积分记录实体对象 */
  @Resource private PointsLog pointsLog;
  /** 用户信息服务对象 */
  @Resource private IUserService userService;
  /** 积分记录服务对象 */
  @Resource private IPointsLogService pointsLogService;
  /** 积分头衔服务对象 */
  @Resource private IPointsService pointsService;

  /**
   签到获取获取判断

   @param userId 用户编号
   @return 是否成功
   */
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {NullPointerException.class})
  public boolean dailyCheck(String userId) {
    // 设置积分记录描述
    pointsLog.setLogDesc(PointsLogDescEnum.DAILY_CHECK.getValue());

    boolean b = pointsLogAdd(userId, PointsLogDescEnum.DAILY_CHECK.getPoints());
    if (b) {
      User user = userService.getById(userId);
      Points points = pointsService.getById(user.getPointsId());
      user.setPoints(points);
      return updateUser(user, PointsLogDescEnum.DAILY_CHECK.getPoints());
    }
    throw new NullPointerException();
  }

  /**
   * 更新下一等级
   *
   * @param user 用户对象
   * @param points 积分
   * @return 是否成功
   */
  private Boolean updateUser(User user, Integer points) {
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

  /**
   * 答题增加积分记录等
   *
   * @param userId 用户编号
   * @param points 积分
   * @param pointsLogDescEnum 记录描述
   */
  @Transactional
  public boolean answerQuestion(
      String userId, Integer points, PointsLogDescEnum pointsLogDescEnum) {
    // 设置积分记录描述
    pointsLog.setLogDesc(pointsLogDescEnum.getValue());

    boolean save = pointsLogAdd(userId, points);

    // 获取用户信息
    User user = userService.getById(userId);
    // 增加剩余积分
    user.setRemainingPoints(user.getRemainingPoints() + points);
    // 增加用户答题积分
    user.setAnswerPoints(user.getAnswerPoints() + points);
    // 增加累积积分
    user.setAccumulativePoints(user.getAccumulativePoints() + points);

    // 更新用户信息
    Boolean updateUser = updateUser(user, points);

    if (!updateUser || !save) {
      throw new NullPointerException();
    }
    return true;
  }

  /**
   * 增加积分执行
   *
   * @param userId 用户编号
   * @param points 积分
   */
  private boolean pointsLogAdd(String userId, Integer points) {
    // 设置积分记录类型
    pointsLog.setType(PointsLogTypeEnum.ADD);
    return savePointsLog(userId, Long.valueOf(points));
  }

  /**
   * 增加与减少的公共代码执行方法
   *
   * @param userId 用户编号
   * @param points 积分
   */
  @Transactional
  public boolean savePointsLog(String userId, Long points) {
    pointsLog.setId(null);
    // 设置积分值
    pointsLog.setNumber(points);
    // 设置用户编号
    pointsLog.setUserId(userId);
    // 创建积分记录信息
    return pointsLogService.save(pointsLog);
  }
}
