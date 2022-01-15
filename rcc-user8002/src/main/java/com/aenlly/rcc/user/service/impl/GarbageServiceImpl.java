package com.aenlly.rcc.user.service.impl;

import com.aenlly.rcc.entity.Garbage;
import com.aenlly.rcc.mapper.GarbageMapper;
import com.aenlly.rcc.user.service.IGarbageService;
import com.aenlly.rcc.utils.wrapper.QueryWrapperUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author aenlly
 * @since 2021-12-12
 */
@Service
public class GarbageServiceImpl extends ServiceImpl<GarbageMapper, Garbage>
    implements IGarbageService {

  /**
   * 根据垃圾类型查询
   *
   * @param garbageType 垃圾类型
   * @return 结果集
   */
  @Override
  public Garbage getByType(String garbageType) {
    Wrapper<Garbage> wrapper = QueryWrapperUtil.queryByGarbageType(garbageType);
    return baseMapper.selectOne(wrapper);
  }
}
