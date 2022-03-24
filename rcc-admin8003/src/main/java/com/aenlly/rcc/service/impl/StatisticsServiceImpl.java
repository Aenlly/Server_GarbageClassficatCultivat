package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.service.IPointsLogService;
import com.aenlly.rcc.service.IStatisticsService;
import com.aenlly.rcc.service.IUserService;
import com.aenlly.rcc.entity.CollectEntity;
import com.aenlly.rcc.entity.LikeEntity;
import com.aenlly.rcc.entity.PointsLog;
import com.aenlly.rcc.entity.User;
import com.aenlly.rcc.service.ICollectEntityService;
import com.aenlly.rcc.service.ILikeEntityService;
import com.aenlly.rcc.service.ISearchUserService;
import com.aenlly.rcc.utils.enums.QueryTimeTypeEnum;
import com.aenlly.rcc.utils.wrapper.StatisticsWrapperUtil;
import com.aenlly.rcc.vo.SearchNameChartVo;
import com.aenlly.rcc.vo.SearchTypeChartVo;
import com.aenlly.rcc.vo.StatisticsVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Aenlly
 * @create by date 2022/02/15 15:14
 * @projectName RefuseClassificationCultivate
 */
@Service
public class StatisticsServiceImpl implements IStatisticsService {

  /** 搜索记录的服务对象 */
  @Resource private ISearchUserService searchUserService;
  /** 用户信息的服务对象 */
  @Resource private IUserService userService;
  /** 积分记录的服务对象 */
  @Resource private IPointsLogService pointsLogService;

  /** 收藏实体的服务对象 */
  @Resource private ICollectEntityService collectEntityService;

  /** 点赞实体的服务对象 */
  @Resource private ILikeEntityService likeEntityService;

  /** 返回的实体类 */
  @Resource private StatisticsVo entity;

  /**
   * 获取统计数据
   *
   * @param typeEnum 时间统计范围
   * @return 所需数据
   */
  @Override
  public StatisticsVo getInit(QueryTimeTypeEnum typeEnum) {
    // 获取搜索内容分组的操作对象
    Wrapper<SearchNameChartVo> searchNameChart = StatisticsWrapperUtil.getSearchNameChart(typeEnum);
    // 查询结果
    List<Map<String, SearchNameChartVo>> nameChartVoList =
        searchUserService.getChart(searchNameChart);

    // 获取搜索类型分组的操作对象
    Wrapper<SearchTypeChartVo> searchTypeChart = StatisticsWrapperUtil.getSearchTypeChart(typeEnum);
    // 查询结果
    List<Map<String, SearchTypeChartVo>> typeChartVoList =
        searchUserService.getChart(searchTypeChart);

    // 获取搜索类型分组的操作对象
    Wrapper<User> userCount = StatisticsWrapperUtil.getUserCount(typeEnum);
    long user = userService.count(userCount);
    // 获取搜索类型分组的操作对象
    Wrapper<PointsLog> pointsLogCount = StatisticsWrapperUtil.getPointsLogCount(typeEnum);
    long pointsLog = pointsLogService.count(pointsLogCount);
    // 获取搜索类型分组的操作对象
    Wrapper<CollectEntity> collectCount = StatisticsWrapperUtil.getCollectCount(typeEnum);
    long collect = collectEntityService.count(collectCount);
    // 获取搜索类型分组的操作对象
    Wrapper<LikeEntity> likeCount = StatisticsWrapperUtil.getLikeCount(typeEnum);
    long like = likeEntityService.count(likeCount);

    System.out.println(nameChartVoList);
    entity.setColumnChart(nameChartVoList);
    entity.setPieChart(typeChartVoList);
    entity.setUserCount(user);
    entity.setSignCount(pointsLog);
    entity.setCollectCount(collect);
    entity.setLikeCount(like);
    System.out.println(entity);
    return entity;
  }
}
