package com.aenlly.rcc.admin.service;

import com.aenlly.rcc.entity.SearchUser;
import com.aenlly.rcc.entity.SearchUserChartView;
import com.aenlly.rcc.enums.SearchTypeEnum;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * 搜索记录服务接口
 *
 * @author Aenlly
 * @create by date 2022/01/26 17:04
 * @projectName RefuseClassificationCultivate
 */
public interface ISearchRecordService {
  /**
   * 查询信息集合
   *
   * @param page 分页对象
   * @param typeEnum 搜索类型
   * @return 分页对象
   */
  IPage<SearchUser> getList(Page<SearchUser> page, SearchTypeEnum typeEnum);

  /**
   * 获取搜索类型集合
   *
   * @return 搜索类型集合
   */
  List<SearchTypeEnum> getType();

  /**
   * 获取图表所需数据集合
   *
   * @return 数据集合
   */
  List<SearchUserChartView> getSearchChart();
}
