package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.entity.PointsLog;
import com.aenlly.rcc.mapper.PointsLogMapper;
import com.aenlly.rcc.service.IPointsLogService;
import com.aenlly.rcc.utils.QueryWrapperUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 服务实现类
 *
 * @author aenlly
 * @since 2021-12-16
 */
@Service
public class PointsLogServiceImpl extends ServiceImpl<PointsLogMapper, PointsLog>
    implements IPointsLogService {

  /**
   * 根据用户编号-积分记录类型查询
   *
   * @param userId 用户编号
   * @param type 积分记录类型
   * @return 积分记录列表
   */
  @Override
  public List<PointsLog> getPointsLogByUserIdList(String userId, Integer type) {
    Wrapper<PointsLog> wrapper = QueryWrapperUtil.getPointsLogBy(userId, type);
    return baseMapper.selectList(wrapper);
  }
}
