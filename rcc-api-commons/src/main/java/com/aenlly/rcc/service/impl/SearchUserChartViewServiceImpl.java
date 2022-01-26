package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.entity.SearchUserChartView;
import com.aenlly.rcc.mapper.SearchUserChartViewMapper;
import com.aenlly.rcc.service.ISearchUserChartViewService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 搜索记录图表数据 VIEW 服务实现类
 *
 * @author aenlly
 * @since 2022-01-26
 */
@Service
public class SearchUserChartViewServiceImpl
    extends ServiceImpl<SearchUserChartViewMapper, SearchUserChartView>
    implements ISearchUserChartViewService {}
