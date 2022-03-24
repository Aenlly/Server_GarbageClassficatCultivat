package com.aenlly.rcc.service;

import com.aenlly.rcc.entity.Points;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 积分头衔 服务类
 *
 * @author aenlly
 * @since 2022-01-30
 */
public interface IPointsService extends IService<Points> {

  /**
   * 查询信息集合
   *
   * @param page 分页对象
   * @param text 查询内容
   * @return 分页对象
   */
  IPage<Points> getList(Page<Points> page, String text);
}
