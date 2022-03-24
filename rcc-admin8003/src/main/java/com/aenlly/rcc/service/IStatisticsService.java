package com.aenlly.rcc.service;

import com.aenlly.rcc.utils.enums.QueryTimeTypeEnum;
import com.aenlly.rcc.vo.StatisticsVo;

/**
 * @author Aenlly
 * @create by date 2022/02/15 15:14
 * @projectName RefuseClassificationCultivate
 */
public interface IStatisticsService {

  /**
   * 获取统计数据
   *
   * @param typeEnum 时间统计范围
   * @return 所需数据
   */
  StatisticsVo getInit(QueryTimeTypeEnum typeEnum);
}
