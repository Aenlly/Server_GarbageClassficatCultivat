package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.entity.Points;
import com.aenlly.rcc.mapper.PointsMapper;
import com.aenlly.rcc.service.IPointsService;
import com.aenlly.rcc.utils.wrapper.PointsWrapperUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author aenlly
 * @since 2021-12-11
 */
@Service
public class PointsServiceImpl extends ServiceImpl<PointsMapper, Points> implements IPointsService {
  /**
   * 根据累积积分获得下一等级
   *
   * @param points 累积积分
   * @return 下一等级实体信息
   */
  @Override
  public Points getNextLevel(Integer points) {
    Wrapper<Points> wrapper = PointsWrapperUtil.getNextLevel(points);
    return baseMapper.selectOne(wrapper);
  }

  /**
   * 根据累积积分查询当前等级
   *
   * @param points 累积积分
   * @return 当前等级
   */
  @Override
  public Points getCurrentLevel(Long points) {
    Wrapper<Points> wrapper = PointsWrapperUtil.getCurrentLevel(points);
    return baseMapper.selectOne(wrapper);
  }
}
