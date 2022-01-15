package com.aenlly.rcc.user.service;

import com.aenlly.rcc.entity.Garbage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 服务类
 *
 * @author aenlly
 * @since 2021-12-12
 */
public interface IGarbageService extends IService<Garbage> {

  /**
   * 根据垃圾类型查询
   *
   * @param garbageType 垃圾类型
   * @return 结果集
   */
  Garbage getByType(String garbageType);
}
