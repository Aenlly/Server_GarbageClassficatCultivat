package com.aenlly.rcc.utils;

import com.aenlly.rcc.entity.*;
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
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper
        .select(
            "user_id",
            "nick_name",
            "avatar_url",
            "accumulative_points",
            "remaining_points",
            "answer_points",
            "points_id")
        .eq("user_id", id);
    return queryWrapper;
  }

  /**
   * 获得用户信息根据积分排序的操作对象
   *
   * @return 查询对象
   */
  public static Wrapper<User> getUserListByPoint() {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper
        .select("nick_name", "avatar_url", "accumulative_points")
        .orderByDesc("accumulative_points");
    return queryWrapper;
  }

  /**
   * 获得根据垃圾类型查询操作对象
   *
   * @param garbageType 垃圾类型
   * @return 查询对象
   */
  public static Wrapper<Garbage> queryByGarbageType(String garbageType) {
    QueryWrapper<Garbage> queryWrapper = new QueryWrapper<>();
    queryWrapper
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
    return queryWrapper;
  }

  /**
   * 获得根据垃圾分类所属编号标识查询操作对象
   *
   * @param garbageId 垃圾类型所属编号类型
   * @return 查询对象
   */
  public static Wrapper<GarbageList> queryByGarbageId(Integer garbageId) {
    QueryWrapper<GarbageList> queryWrapper = new QueryWrapper<>();
    queryWrapper.select("id", "garbage_name", "img_url").eq("garbage_id", garbageId);
    return queryWrapper;
  }

  /**
   * 获得热门信息查询操作对象
   *
   * @return 查询对象
   */
  public static Wrapper<HotInfoUserView> queryHotInfoUserList() {
    QueryWrapper<HotInfoUserView> queryWrapper = new QueryWrapper<>();
    queryWrapper.select("hot_info_id", "img_url", "hot_info_title", "hot_info_desc");
    return queryWrapper;
  }

  /**
   * 根据资讯标题获得热门信息查询操作对象
   *
   * @return 查询对象
   */
  public static Wrapper<HotInfoUserView> getHotInfoUserByTitleList(String title) {
    QueryWrapper<HotInfoUserView> queryWrapper = new QueryWrapper<>();
    queryWrapper
        .select("hot_info_id", "img_url", "hot_info_title", "hot_info_desc")
        .likeRight("hot_info_title", title);
    return queryWrapper;
  }

  /**
   * 根据实体名称与数据id获得查询点赞操作对象
   *
   * @return 查询对象
   */
  public static Wrapper<LikeEntity> getLikeCountByDataId(String entityName, String dataId) {
    QueryWrapper<LikeEntity> queryWrapper = new QueryWrapper<>();
    queryWrapper.select("like_id").eq("entity_name", entityName).eq("data_id", dataId);
    return queryWrapper;
  }

  /**
   * 根据用户id，实体名称与数据id 获得判断是否点赞操作对象
   *
   * @return 查询对象
   */
  public static Wrapper<LikeEntity> getIsLikeByUserId(
      String userId, String entityName, String dataId) {
    QueryWrapper<LikeEntity> queryWrapper = new QueryWrapper<>();
    queryWrapper
        .select("like_id")
        .eq("user_id", userId)
        .eq("entity_name", entityName)
        .eq("data_id", dataId);
    return queryWrapper;
  }

  /**
   * 根据实体名称与数据id获得查询收藏操作对象
   *
   * @return 查询对象
   */
  public static Wrapper<CollectEntity> getCollectCountByDataId(String entityName, String dataId) {
    QueryWrapper<CollectEntity> queryWrapper = new QueryWrapper<>();
    queryWrapper.select("collect_id").eq("entity_name", entityName).eq("data_id", dataId);
    return queryWrapper;
  }

  /**
   * 根据用户id，实体名称与数据id 获得判断是否收藏操作对象
   *
   * @return 查询对象
   */
  public static Wrapper<CollectEntity> getIsCollectByUserId(
      String userId, String entityName, String dataId) {
    QueryWrapper<CollectEntity> queryWrapper = new QueryWrapper<>();
    queryWrapper
        .select("collect_id")
        .eq("user_id", userId)
        .eq("entity_name", entityName)
        .eq("data_id", dataId);
    return queryWrapper;
  }

  /**
   * 用户首页视频获得操作对象
   *
   * @return 查询对象
   */
  public static Wrapper<VideoUserView> getByChekTop() {
    QueryWrapper<VideoUserView> queryWrapper = new QueryWrapper<>();
    queryWrapper.select("video_url").eq("video_check", "置顶");
    return queryWrapper;
  }

  /**
   * 用户服务-公益视频根据标题获得操作对象
   *
   * @param title 标题
   * @return 查询对象
   */
  public static Wrapper<VideoUserView> getVideoByTitleList(String title) {
    QueryWrapper<VideoUserView> queryWrapper = new QueryWrapper<>();
    queryWrapper.likeRight("video_title", title);
    return queryWrapper;
  }

  /**
   * 获得用户首页-文本搜索根据文本查询对象
   *
   * @param c 搜索文本
   * @return 查询对象
   */
  public static Wrapper<GarbageLibrary> getSearchText(char c) {
    QueryWrapper<GarbageLibrary> queryWrapper = new QueryWrapper<>();
    queryWrapper.likeRight("name", String.valueOf(c));
    return queryWrapper;
  }

  /**
   * 用户首页-搜索记录，根据用户id获得操作对象
   *
   * @param userId 用户编号
   * @return 查询对象
   */
  public static Wrapper<UserSearch> getSearchList(String userId) {
    QueryWrapper<UserSearch> queryWrapper = new QueryWrapper<>();
    queryWrapper.select("name", "type").eq("user_id", userId).orderBy(true, false, "create_time");
    return queryWrapper;
  }

  /**
   * 用户首页-搜索记录，根据用户id与搜索垃圾名称获得操作对象
   *
   * @param userId 用户编号
   * @param name 搜索垃圾名称
   * @return 查询对象
   */
  public static Wrapper<UserSearch> getSearchByName(String userId, String name) {
    QueryWrapper<UserSearch> queryWrapper = new QueryWrapper<>();
    queryWrapper
        .select("name", "type")
        .eq("user_id", userId)
        .likeRight("name", name)
        .orderBy(true, false, "create_time");
    return queryWrapper;
  }

  /**
   * 根据用户id与查询标题来获得操作对象
   *
   * @param userId 用户id
   * @param name 查询标题
   * @return 查询对象
   */
  public static Wrapper<CollectEntity> getUserCollectListBy(String userId, String name) {
    QueryWrapper<CollectEntity> queryWrapper = new QueryWrapper<>();
    queryWrapper
        .select("collect_id", "data_title", "data_desc", "data_id", "img_url", "entity_name")
        .eq("user_id", userId)
        .like("data_title", name);
    return queryWrapper;
  }

  /**
   * 根据礼品名称、类型获得操作对象
   *
   * @param name 礼品名称
   * @param type 礼品类型
   * @return 查询对象
   */
  public static Wrapper<GiftListView> getUserGiftList(String name, Integer type) {
    QueryWrapper<GiftListView> queryWrapper = new QueryWrapper<>();
    queryWrapper.select("gift_id", "name", "img_url", "point", "price").like("name", name);
    if (type != -1) {
      queryWrapper.eq("type_id", type);
    }
    return queryWrapper;
  }

  /**
   * 获得用户积分礼品显示的类型内容操作对象
   *
   * @return 查询对象
   */
  public static Wrapper<GiftType> getUserGiftTypeList() {
    QueryWrapper<GiftType> queryWrapper = new QueryWrapper<>();
    queryWrapper.select("id", "type_name", "img_url").eq("gift_show", 1).last("limit 4");
    return queryWrapper;
  }
}
