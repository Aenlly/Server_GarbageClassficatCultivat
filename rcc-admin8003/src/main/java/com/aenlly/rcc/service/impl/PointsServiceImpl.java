package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.service.IPointsService;
import com.aenlly.rcc.entity.Points;
import com.aenlly.rcc.mapper.PointsMapper;
import com.aenlly.rcc.utils.wrapper.PointsWrapperUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 积分头衔 服务实现类
 *
 * @author aenlly
 * @since 2022-01-30
 */
@Service
public class PointsServiceImpl extends ServiceImpl<PointsMapper, Points> implements IPointsService {
  /**
   * 查询信息集合
   *
   * @param page 分页对象
   * @param text 查询内容
   * @return 分页对象
   */
  @Override
  public IPage<Points> getList(Page<Points> page, String text) {
    Wrapper<Points> wrapper = PointsWrapperUtil.queryListPage(text);
    return this.page(page, wrapper);
  }
}
