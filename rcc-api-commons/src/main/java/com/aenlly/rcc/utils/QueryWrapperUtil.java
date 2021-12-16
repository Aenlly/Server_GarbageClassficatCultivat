package com.aenlly.rcc.utils;

import com.aenlly.rcc.entity.*;
import com.aenlly.rcc.enums.PointsLogDescEnum;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.io.Serializable;

/**
 * @author Aenlly
 * @create by date 2021/12/12 14:15
 * @projectName RefuseClassificationCultivate
 */
public class QueryWrapperUtil {
  private QueryWrapperUtil() {}

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
   * 获得根据垃圾类型查询操作对象
   *
   * @param garbageType 垃圾类型
   * @return 查询对象
   */
  public static Wrapper<Garbage> queryByGarbageType(String garbageType) {
    QueryWrapper<Garbage> wrapper = new QueryWrapper<>();
    wrapper
        .select(
            "garbage_id",
            "video_url",
            "garbage_type",
            "name_cn",
            "name_en",
            "img_url",
            "text_title",
            "text_desc",
            "bg_color")
        .eq("garbage_type", garbageType);
    return wrapper;
  }

  /**
   * 获得根据垃圾分类所属编号标识查询操作对象
   *
   * @param garbageId 垃圾类型所属编号类型
   * @return 查询对象
   */
  public static Wrapper<GarbageList> queryByGarbageId(Integer garbageId) {
    QueryWrapper<GarbageList> wrapper = new QueryWrapper<>();
    wrapper.select("id", "garbage_name", "img_url").eq("garbage_id", garbageId);
    return wrapper;
  }

  /**
   * 获得热门信息查询操作对象
   *
   * @return 查询对象
   */
  public static Wrapper<HotInfoUserView> queryHotInfoUserList() {
    QueryWrapper<HotInfoUserView> wrapper = new QueryWrapper<>();
    wrapper.select("hot_info_id", "img_url", "hot_info_title", "hot_info_desc");
    return wrapper;
  }

  /**
   * 根据资讯标题获得热门信息查询操作对象
   *
   * @return 查询对象
   */
  public static Wrapper<HotInfoUserView> getHotInfoUserByTitleList(String title) {
    QueryWrapper<HotInfoUserView> wrapper = new QueryWrapper<>();
    wrapper
        .select("hot_info_id", "img_url", "hot_info_title", "hot_info_desc")
        .likeRight("hot_info_title", title);
    return wrapper;
  }

  /**
   * 根据实体名称与数据id获得查询点赞操作对象
   *
   * @return 查询对象
   */
  public static Wrapper<LikeEntity> getLikeCountByDataId(String entityName, String dataId) {
    QueryWrapper<LikeEntity> wrapper = new QueryWrapper<>();
    wrapper.select("like_id").eq("entity_name", entityName).eq("data_id", dataId);
    return wrapper;
  }

  /**
   * 根据用户id，实体名称与数据id 获得判断是否点赞操作对象
   *
   * @return 查询对象
   */
  public static Wrapper<LikeEntity> getIsLikeByUserId(
      String userId, String entityName, String dataId) {
    QueryWrapper<LikeEntity> wrapper = new QueryWrapper<>();
    wrapper
        .select("like_id")
        .eq("user_id", userId)
        .eq("entity_name", entityName)
        .eq("data_id", dataId);
    return wrapper;
  }

  /**
   * 根据实体名称与数据id获得查询收藏操作对象
   *
   * @return 查询对象
   */
  public static Wrapper<CollectEntity> getCollectCountByDataId(String entityName, String dataId) {
    QueryWrapper<CollectEntity> wrapper = new QueryWrapper<>();
    wrapper.select("collect_id").eq("entity_name", entityName).eq("data_id", dataId);
    return wrapper;
  }

  /**
   * 根据用户id，实体名称与数据id 获得判断是否收藏操作对象
   *
   * @return 查询对象
   */
  public static Wrapper<CollectEntity> getIsCollectByUserId(
      String userId, String entityName, String dataId) {
    QueryWrapper<CollectEntity> wrapper = new QueryWrapper<>();
    wrapper
        .select("collect_id")
        .eq("user_id", userId)
        .eq("entity_name", entityName)
        .eq("data_id", dataId);
    return wrapper;
  }

  /**
   * 用户首页视频获得操作对象
   *
   * @return 查询对象
   */
  public static Wrapper<VideoUserView> getByChekTop() {
    QueryWrapper<VideoUserView> wrapper = new QueryWrapper<>();
    wrapper.select("video_url").eq("video_check", "置顶");
    return wrapper;
  }

  /**
   * 用户服务-公益视频根据标题获得操作对象
   *
   * @param title 标题
   * @return 查询对象
   */
  public static Wrapper<VideoUserView> getVideoByTitleList(String title) {
    QueryWrapper<VideoUserView> wrapper = new QueryWrapper<>();
    wrapper.likeRight("video_title", title);
    return wrapper;
  }

  /**
   * 获得用户首页-文本搜索根据文本查询对象
   *
   * @param c 搜索文本
   * @return 查询对象
   */
  public static Wrapper<GarbageLibrary> getSearchText(char c) {
    QueryWrapper<GarbageLibrary> wrapper = new QueryWrapper<>();
    wrapper.likeRight("name", String.valueOf(c));
    return wrapper;
  }

  /**
   * 用户首页-搜索记录，根据用户id获得操作对象
   *
   * @param userId 用户编号
   * @return 查询对象
   */
  public static Wrapper<UserSearch> getSearchList(String userId) {
    QueryWrapper<UserSearch> wrapper = new QueryWrapper<>();
    wrapper.select("name", "type").eq("user_id", userId).orderBy(true, false, "create_time");
    return wrapper;
  }

  /**
   * 用户首页-搜索记录，根据用户id与搜索垃圾名称获得操作对象
   *
   * @param userId 用户编号
   * @param name 搜索垃圾名称
   * @return 查询对象
   */
  public static Wrapper<UserSearch> getSearchByName(String userId, String name) {
    QueryWrapper<UserSearch> wrapper = new QueryWrapper<>();
    wrapper
        .select("name", "type")
        .eq("user_id", userId)
        .likeRight("name", name)
        .orderBy(true, false, "create_time");
    return wrapper;
  }

  /**
   * 根据用户id与查询标题来获得操作对象
   *
   * @param userId 用户id
   * @param name 查询标题
   * @return 查询对象
   */
  public static Wrapper<CollectEntity> getUserCollectListBy(String userId, String name) {
    QueryWrapper<CollectEntity> wrapper = new QueryWrapper<>();
    wrapper
        .select("collect_id", "data_title", "data_desc", "data_id", "img_url", "entity_name")
        .eq("user_id", userId)
        .like("data_title", name);
    return wrapper;
  }

  /**
   * 根据礼品名称、类型获得操作对象
   *
   * @param name 礼品名称
   * @param type 礼品类型
   * @return 查询对象
   */
  public static Wrapper<GiftListView> getUserGiftList(String name, Integer type) {
    QueryWrapper<GiftListView> wrapper = new QueryWrapper<>();
    wrapper.select("gift_id", "name", "img_url", "point", "price").like("name", name);
    if (type != -1) {
      wrapper.eq("type_id", type);
    }
    return wrapper;
  }

  /**
   * 获得用户积分礼品显示的类型内容操作对象
   *
   * @return 查询对象
   */
  public static Wrapper<GiftType> getUserGiftTypeList() {
    QueryWrapper<GiftType> wrapper = new QueryWrapper<>();
    wrapper.select("id", "type_name", "img_url").eq("gift_show", 1).last("limit 4");
    return wrapper;
  }

  /**
   * 根据用户编号与订单状态获得操作对象
   *
   * @param userId 用户编号
   * @param state 操作对象
   * @return 查询对象
   */
  public static Wrapper<OrderUserView> getOrderUserList(String userId, String state) {
    QueryWrapper<OrderUserView> wrapper = new QueryWrapper<>();
    wrapper.eq("user_id", userId).eq("state", state).orderByDesc("create_time");
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
  public static Wrapper<Points> getCurrentLevel(Integer points) {
    QueryWrapper<Points> wrapper = new QueryWrapper<>();
    wrapper
        .select("points_id", "points_name", "points_require")
        .le("points_require", points)
        .orderByDesc("points_require")
        .last("limit 1");
    return wrapper;
  }

  public static Wrapper<PointsLog> getPointsLogBy(String userId, Integer type) {
    QueryWrapper<PointsLog> wrapper = new QueryWrapper<>();
    wrapper.select("log_desc", "number", "type", "create_time").eq("user_id", userId);
    if (type != 0) {
      wrapper.eq("type", type);
    }
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
