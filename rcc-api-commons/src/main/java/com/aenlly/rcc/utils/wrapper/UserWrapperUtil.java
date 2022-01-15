package com.aenlly.rcc.utils.wrapper;

import com.aenlly.rcc.entity.User;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.io.Serializable;

/**
 * 用户信息的实体封装操作对象获取工具类
 *
 * @author Aenlly
 * @create by date 2022/01/16 0:24
 * @projectName RefuseClassificationCultivate
 */
public class UserWrapperUtil {

  /**
   * 根据查询条件获取操作对象
   *
   * @param text 查询内容
   * @return 查询对象
   */
  public static Wrapper<User> queryListPage(String text) {
    QueryWrapper<User> wrapper = new QueryWrapper<>();
    wrapper.like("name", text).orderByDesc("show_flag").orderBy(true, false, "update_time");
    return wrapper;
  }

  /**
   * 根据用户id获得操作对象
   *
   * @param id 用户id
   * @return 查询对象
   */
  public static Wrapper<User> getUserById(Serializable id) {
    QueryWrapper<User> wrapper = new QueryWrapper<>();
    wrapper
        .select(
            "user_id",
            "nick_name",
            "avatar_url",
            "accumulative_points",
            "remaining_points",
            "answer_points",
            "points_id")
        .eq("user_id", id);
    return wrapper;
  }

  /**
   * 获得用户信息根据积分排序的操作对象
   *
   * @return 查询对象
   */
  public static Wrapper<User> getUserListByPoint() {
    QueryWrapper<User> wrapper = new QueryWrapper<>();
    wrapper
        .select("nick_name", "avatar_url", "accumulative_points")
        .orderByDesc("accumulative_points");
    return wrapper;
  }

  /**
   * 获得用户信息根据答题积分排序的操作对象
   *
   * @return 查询对象
   */
  public static Wrapper<User> getUserListByAnswerPoints() {
    QueryWrapper<User> wrapper = new QueryWrapper<>();
    wrapper.select("nick_name", "avatar_url", "answer_points").orderByDesc("answer_points");
    return wrapper;
  }
}
