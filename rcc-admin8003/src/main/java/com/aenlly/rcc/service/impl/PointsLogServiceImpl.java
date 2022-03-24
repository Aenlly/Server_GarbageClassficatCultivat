package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.service.IPointsLogService;
import com.aenlly.rcc.entity.PointsLog;
import com.aenlly.rcc.mapper.PointsLogMapper;
import com.aenlly.rcc.utils.enums.QueryPointsLogTypeEnum;
import com.aenlly.rcc.utils.wrapper.PointsWrapperUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用户积分记录 服务实现类
 *
 * @author aenlly
 * @since 2022-01-16
 */
@Service
public class PointsLogServiceImpl extends ServiceImpl<PointsLogMapper, PointsLog>
    implements IPointsLogService {
  /**
   * 查询信息集合
   *
   * @param page 分页对象
   * @param queryType 查询类型
   * @param text 查询内容
   * @return 分页对象
   */
  @Override
  public IPage<PointsLog> getList(
      Page<PointsLog> page, QueryPointsLogTypeEnum queryType, String text) {
    Wrapper<PointsLog> wrapper = PointsWrapperUtil.queryListPage(queryType, text);
    return this.page(page, wrapper);
  }
}
