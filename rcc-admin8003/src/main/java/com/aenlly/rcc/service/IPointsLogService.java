package com.aenlly.rcc.service;

import com.aenlly.rcc.entity.PointsLog;
import com.aenlly.rcc.utils.enums.QueryPointsLogTypeEnum;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 用户积分记录 服务类
 *
 * @author aenlly
 * @since 2022-01-16
 */
public interface IPointsLogService extends IService<PointsLog> {

  /**
   * 查询信息集合
   *
   * @param page 分页对象
   * @param queryType 查询类型
   * @param text 查询内容
   * @return 分页对象
   */
  IPage<PointsLog> getList(Page<PointsLog> page, QueryPointsLogTypeEnum queryType, String text);
}
