package com.aenlly.rcc.admin.service.impl;

import com.aenlly.rcc.admin.service.ISearchRecordService;
import com.aenlly.rcc.entity.SearchUser;
import com.aenlly.rcc.enums.SearchTypeEnum;
import com.aenlly.rcc.service.ISearchUserService;
import com.aenlly.rcc.utils.wrapper.SearchWrapperUtil;
import com.aenlly.rcc.vo.SearchNameChartVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 搜索记录服务实现类
 *
 * @author Aenlly
 * @create by date 2022/01/26 17:05
 * @projectName RefuseClassificationCultivate
 */
@Service
public class SearchRecordServiceImpl implements ISearchRecordService {
  /** 用户搜索记录服务对象 */
  @Resource private ISearchUserService searchUserService;

  /**
   * 查询信息集合
   *
   * @param page 分页对象
   * @param typeEnum 搜索类型
   * @return 分页对象
   */
  @Override
  public IPage<SearchUser> getList(Page<SearchUser> page, SearchTypeEnum typeEnum) {
    Wrapper<SearchUser> wrapper = SearchWrapperUtil.queryListPage(typeEnum);
    return searchUserService.page(page, wrapper);
  }

  /**
   * 获取搜索类型集合
   *
   * @return 搜索类型集合
   */
  @Override
  public List<SearchTypeEnum> getType() {
    List<SearchTypeEnum> list = new ArrayList<>();
    list.add(SearchTypeEnum.SEARCH_TEXT);
    list.add(SearchTypeEnum.SEARCH_PICTURE);
    list.add(SearchTypeEnum.SEARCH_VOICE);
    return list;
  }

  /**
   * 获取图表所需数据集合
   *
   * @return 数据集合
   */
  @Override
  public List<Map<String, SearchNameChartVo>> getSearchChart() {
    Wrapper<SearchNameChartVo> wrapper = SearchWrapperUtil.getSearchNameChart();
    return searchUserService.getChart(wrapper);
  }
}
