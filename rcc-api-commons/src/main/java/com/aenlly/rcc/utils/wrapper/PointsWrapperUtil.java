package com.aenlly.rcc.utils.wrapper;

import com.aenlly.rcc.entity.Points;
import com.aenlly.rcc.entity.PointsLog;
import com.aenlly.rcc.enums.PointsLogDescEnum;
import com.aenlly.rcc.utils.enums.QueryPointsLogTypeEnum;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * @author Aenlly
 * @create by date 2022/01/30 19:20
 * @projectName RefuseClassificationCultivate
 */
public class PointsWrapperUtil {

  /**
   * 根据条件获取积分头衔实体操作对象
   *
   * @param text 查询内容
   * @return 查询对象
   */
  public static Wrapper<Points> queryListPage(String text) {
    QueryWrapper<Points> wrapper = new QueryWrapper<>();
    wrapper.like("points_name", text).orderBy(true, true, "points_require");
    return wrapper;
  }

  /**
   * 根据条件获取积分记录实体操作对象
   *
   * @param queryType 查询条件
   * @param text 查询内容
   * @return 查询对象
   */
  public static Wrapper<PointsLog> queryListPage(QueryPointsLogTypeEnum queryType, String text) {
    QueryWrapper<PointsLog> wrapper = new QueryWrapper<>();
    wrapper.like(queryType.getValue(), text);
    return wrapper;
  }

  /**
   * 根据累积积分获得下一等级操作对象
   *
   * @param points 累积积分
   * @return 查询对象
   */
  public static Wrapper<Points> getNextLevel(Integer points) {
    QueryWrapper<Points> wrapper = new QueryWrapper<>();
    wrapper
        .select("points_id", "points_name", "points_require")
        .gt("points_require", points)
        .last("limit 1");
    return wrapper;
  }

  /**
   * 根据累积积分获得当前等级操作对象
   *
   * @param points 累积积分
   * @return 查询对象
   */
  public static Wrapper<Points> getCurrentLevel(Long points) {
    QueryWrapper<Points> wrapper = new QueryWrapper<>();
    wrapper
        .select("points_id", "points_name", "points_require")
        .le("points_require", points)
        .orderByDesc("points_require")
        .last("limit 1");
    return wrapper;
  }

  /**
   * 根据用户编号与积分记录类型 获取操作对象
   *
   * @param userId 用户编号
   * @param type 积分记录类型
   * @return 查询对象
   */
  public static Wrapper<PointsLog> getPointsLogBy(String userId, Integer type) {
    QueryWrapper<PointsLog> wrapper = new QueryWrapper<>();
    wrapper.select("log_desc", "number", "type", "create_time").eq("user_id", userId);
    if (type != 0) {
      wrapper.eq("type", type);
    }
    wrapper.orderByDesc("create_time");
    return wrapper;
  }

  /**
   * 签到前根据用户编号判断是否已签到，获得操作对象
   *
   * @param userId 用户编号
   * @return 查询对象
   */
  public static Wrapper<PointsLog> isDailyCheck(String userId) {
    QueryWrapper<PointsLog> wrapper = new QueryWrapper<>();
    wrapper
        .eq("user_id", userId)
        .apply("TO_DAYS(create_time) = TO_DAYS(NOW())") // 查询当天的数据
        .eq("log_desc", PointsLogDescEnum.DAILY_CHECK.getValue());
    return wrapper;
  }
}
