package com.aenlly.rcc.user.service;

import com.aenlly.rcc.entity.GarbageList;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 服务类
 *
 * @author aenlly
 * @since 2021-12-12
 */
public interface IGarbageListService extends IService<GarbageList> {

  /**
   * 根据垃圾分类所属编号标识查询集合
   *
   * @param garbageId 垃圾分类所属编号
   * @return 集合
   */
  List<GarbageList> getByGarbageId(Integer garbageId);
}
