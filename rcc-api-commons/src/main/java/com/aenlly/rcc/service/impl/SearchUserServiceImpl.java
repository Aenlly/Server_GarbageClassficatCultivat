package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.entity.SearchUser;
import com.aenlly.rcc.mapper.SearchUserMapper;
import com.aenlly.rcc.service.ISearchUserService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 首页用户搜索库表 服务实现类
 *
 * @author aenlly
 * @since 2022-01-26
 */
@Service
public class SearchUserServiceImpl extends ServiceImpl<SearchUserMapper, SearchUser>
    implements ISearchUserService {
  /**
   * 获取图表数据
   *
   * @param wrapper 实体封装操作条件
   * @return 图表数据
   */
  @Override
  public <T> List<Map<String, T>> getChart(Wrapper<T> wrapper) {
    return this.baseMapper.getChart(wrapper);
  }
}
