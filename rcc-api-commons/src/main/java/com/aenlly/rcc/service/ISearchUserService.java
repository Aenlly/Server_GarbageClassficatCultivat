package com.aenlly.rcc.service;

import com.aenlly.rcc.entity.SearchUser;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 首页用户搜索库表 服务类
 *
 * @author aenlly
 * @since 2022-01-26
 */
public interface ISearchUserService extends IService<SearchUser> {

  /**
   * 获取图表数据
   *
   * @param wrapper 实体封装操作条件
   * @return 图表数据
   */
  <T> List<Map<String, T>> getChart(Wrapper<T> wrapper);
}
