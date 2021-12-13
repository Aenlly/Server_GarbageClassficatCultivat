package com.aenlly.rcc.utils;

import com.aenlly.rcc.entity.Garbage;
import com.aenlly.rcc.entity.GarbageList;
import com.aenlly.rcc.entity.HotInfoUserView;
import com.aenlly.rcc.entity.LikeEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

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
  public static QueryWrapper<Garbage> queryByGarbageType(String garbageType) {
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
  public static QueryWrapper<GarbageList> queryByGarbageId(Integer garbageId) {
    QueryWrapper<GarbageList> queryWrapper = new QueryWrapper<>();
    queryWrapper.select("id", "garbage_name", "img_url").eq("garbage_id", garbageId);
    return queryWrapper;
  }

  /**
   * 获得热门信息查询操作对象
   *
   * @return 查询对象
   */
  public static QueryWrapper<HotInfoUserView> queryHotInfoUserList() {
    QueryWrapper<HotInfoUserView> queryWrapper = new QueryWrapper<>();
    queryWrapper.select("hot_info_id", "img_url", "hot_info_title", "hot_info_desc");
    return queryWrapper;
  }

  /**
   * 根据资讯标题获得热门信息查询操作对象
   *
   * @return 查询对象
   */
  public static QueryWrapper<HotInfoUserView> getHotInfoUserByTitleList(String title) {
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
  public static QueryWrapper<LikeEntity> getCountByDataId(String entityName, String dataId) {
    QueryWrapper<LikeEntity> queryWrapper = new QueryWrapper<>();
    queryWrapper.select("like_id").eq("entity_name", entityName).eq("data_id", dataId);
    return queryWrapper;
  }

  /**
   * 根据用户id，实体名称与数据id 获得判断是否点赞操作对象
   *
   * @return 查询对象
   */
  public static QueryWrapper<LikeEntity> getIsByUserId(
      String userId, String entityName, String dataId) {
    QueryWrapper<LikeEntity> queryWrapper = new QueryWrapper<>();
    queryWrapper
        .select("like_id")
        .eq("user_id", userId)
        .eq("entity_name", entityName)
        .eq("data_id", dataId);
    return queryWrapper;
  }
}
