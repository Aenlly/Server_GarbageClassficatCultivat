package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.entity.HotInfoUserView;
import com.aenlly.rcc.mapper.HotInfoUserViewMapper;
import com.aenlly.rcc.service.IHotInfoUserViewService;
import com.aenlly.rcc.utils.QueryWrapperUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * VIEW 服务实现类
 *
 * @author aenlly
 * @since 2021-12-12
 */
@Service
public class HotInfoUserViewServiceImpl extends ServiceImpl<HotInfoUserViewMapper, HotInfoUserView>
    implements IHotInfoUserViewService {

  /**
   * 查询用户服务热门资讯展示列表信息
   *
   * @return 列表信息
   */
  @Override
  public List<HotInfoUserView> list() {
    Wrapper<HotInfoUserView> queryWrapper = QueryWrapperUtil.queryHotInfoUserList();
    return baseMapper.selectList(queryWrapper);
  }

  /**
   * 根据标题查询资讯列表
   *
   * @return 资讯列表
   */
  @Override
  public List<HotInfoUserView> getHotInfoUserByTitleList(String title) {
    Wrapper<HotInfoUserView> queryWrapper = QueryWrapperUtil.getHotInfoUserByTitleList(title);
    return baseMapper.selectList(queryWrapper);
  }
}
