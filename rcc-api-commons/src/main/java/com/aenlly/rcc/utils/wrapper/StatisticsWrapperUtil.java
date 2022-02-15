package com.aenlly.rcc.utils.wrapper;

import com.aenlly.rcc.entity.CollectEntity;
import com.aenlly.rcc.entity.LikeEntity;
import com.aenlly.rcc.entity.PointsLog;
import com.aenlly.rcc.entity.User;
import com.aenlly.rcc.enums.PointsLogDescEnum;
import com.aenlly.rcc.utils.enums.QueryTimeTypeEnum;
import com.aenlly.rcc.vo.SearchNameChartVo;
import com.aenlly.rcc.vo.SearchTypeChartVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * @author Aenlly
 * @create by date 2022/02/15 16:47
 * @projectName RefuseClassificationCultivate
 */
public class StatisticsWrapperUtil {

  /**
   * 按照搜索名称分组获得操作对象,前十的数据
   *
   * @param typeEnum 时间条件
   * @return 查询对象
   */
  public static Wrapper<SearchNameChartVo> getSearchNameChart(QueryTimeTypeEnum typeEnum) {
    QueryWrapper<SearchNameChartVo> wrapper = new QueryWrapper<>();
    wrapper.apply(typeEnum.getValue()).groupBy("name").orderBy(true, false, "y").last(" limit 10");
    return wrapper;
  }

  /**
   * 按照搜索类型分组获得操作对象
   *
   * @param typeEnum 时间条件
   * @return 查询对象
   */
  public static Wrapper<SearchTypeChartVo> getSearchTypeChart(QueryTimeTypeEnum typeEnum) {
    QueryWrapper<SearchTypeChartVo> wrapper = new QueryWrapper<>();
    wrapper.apply(typeEnum.getValue()).groupBy("type").orderBy(true, false, "y");
    return wrapper;
  }

  /**
   * 获取新增用户的操作对象
   *
   * @param typeEnum 时间条件
   * @return 查询对象
   */
  public static Wrapper<User> getUserCount(QueryTimeTypeEnum typeEnum) {
    QueryWrapper<User> wrapper = new QueryWrapper<>();
    wrapper.apply(typeEnum.getValue());
    return wrapper;
  }

  /**
   * 获取新增打卡的操作对象
   *
   * @param typeEnum 时间条件
   * @return 查询对象
   */
  public static Wrapper<PointsLog> getPointsLogCount(QueryTimeTypeEnum typeEnum) {
    QueryWrapper<PointsLog> wrapper = new QueryWrapper<>();
    wrapper.apply(typeEnum.getValue()).eq("log_desc", PointsLogDescEnum.DAILY_CHECK.getValue());
    return wrapper;
  }

  /**
   * 获取新增收藏的操作对象
   *
   * @param typeEnum 时间条件
   * @return 查询对象
   */
  public static Wrapper<CollectEntity> getCollectCount(QueryTimeTypeEnum typeEnum) {
    QueryWrapper<CollectEntity> wrapper = new QueryWrapper<>();
    wrapper.apply(typeEnum.getValue());
    return wrapper;
  }

  /**
   * 获取新增点赞的操作对象
   *
   * @param typeEnum 时间条件
   * @return 查询对象
   */
  public static Wrapper<LikeEntity> getLikeCount(QueryTimeTypeEnum typeEnum) {
    QueryWrapper<LikeEntity> wrapper = new QueryWrapper<>();
    wrapper.apply(typeEnum.getValue());
    return wrapper;
  }
}
