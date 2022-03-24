package com.aenlly.rcc.service;

import com.aenlly.rcc.entity.VideoUserView;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 用户公益视频视图 VIEW 服务类
 *
 * @author aenlly
 * @since 2021-12-13
 */
public interface IVideoUserViewService extends IService<VideoUserView> {

  /**
   * 查询置顶视频信息
   *
   * @return 视频信息
   */
  VideoUserView getByChekTop();

  /**
   * 根据公益视频标题查询集合
   *
   * @param title 标题
   * @return 结果集
   */
  List<VideoUserView> getByTitleList(String title);

  /**
   * 通过视频编号，增加分享量
   *
   * @param id 视频编号
   * @return 是否成功
   */
  Boolean upShareCount(Long id);
}
