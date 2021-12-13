package com.aenlly.rcc.service;

import com.aenlly.rcc.entity.HotInfoUserView;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * VIEW 服务类
 *
 * @author aenlly
 * @since 2021-12-12
 */
public interface IHotInfoUserViewService extends IService<HotInfoUserView> {

  /**
   * 查询用户服务热门资讯展示列表信息
   *
   * @return 列表信息
   */
  @Override
  List<HotInfoUserView> list();

  /**
   * 根据标题查询资讯列表
   *
   * @return 资讯列表
   */
  List<HotInfoUserView> getHotInfoUserByTitleList(String title);
}
