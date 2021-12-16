package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.entity.GarbageList;
import com.aenlly.rcc.mapper.GarbageListMapper;
import com.aenlly.rcc.service.IGarbageListService;
import com.aenlly.rcc.utils.QueryWrapperUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 服务实现类
 *
 * @author aenlly
 * @since 2021-12-12
 */
@Service
public class GarbageListServiceImpl extends ServiceImpl<GarbageListMapper, GarbageList>
    implements IGarbageListService {

  /**
   * 根据垃圾分类所属编号标识查询集合
   *
   * @param garbageId 垃圾分类所属编号
   * @return 集合
   */
  @Override
  public List<GarbageList> getByGarbageId(Integer garbageId) {
    Wrapper<GarbageList> wrapper = QueryWrapperUtil.queryByGarbageId(garbageId);
    return baseMapper.selectList(wrapper);
  }
}
