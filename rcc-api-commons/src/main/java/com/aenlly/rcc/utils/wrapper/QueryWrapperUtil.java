package com.aenlly.rcc.utils.wrapper;

import com.aenlly.rcc.entity.*;
import com.aenlly.rcc.enums.OrderStateEnum;
import com.aenlly.rcc.enums.VideoCheckEnum;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Aenlly
 * @create by date 2021/12/12 14:15
 * @projectName RefuseClassificationCultivate
 */
public class QueryWrapperUtil {
  private QueryWrapperUtil() {}

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
    wrapper.select("video_url").eq("video_check", VideoCheckEnum.TOP);
    return wrapper;
  }

  /**
   * 根据用户id与查询标题 变废为宝的收藏需要加上实体名称 来获得操作对象
   *
   * @param userId 用户id
   * @param name 查询标题
   * @param entityName 实体名称，可为空
   * @return 查询对象
   */
  public static Wrapper<CollectEntity> getUserCollectListBy(
      String userId, String name, String entityName) {
    QueryWrapper<CollectEntity> wrapper = new QueryWrapper<>();
    wrapper
        .select(
            "collect_id",
            "data_title",
            "data_desc",
            "data_id",
            "img_url",
            "entity_name",
            "text_tag")
        .eq("user_id", userId)
        .like("data_title", name);
    if (StringUtils.isNotBlank(entityName)) {
      wrapper.eq("entity_name", entityName);
    }
    return wrapper;
  }

  /**
   * 根据用户编号与订单状态获得操作对象
   *
   * @param userId 用户编号
   * @param state 操作对象
   * @return 查询对象
   */
  public static Wrapper<OrderUserView> getOrderUserList(String userId, OrderStateEnum state) {
    QueryWrapper<OrderUserView> wrapper = new QueryWrapper<>();
    wrapper.eq("user_id", userId).eq("state", state.getName()).orderByDesc("create_time");
    return wrapper;
  }
}
