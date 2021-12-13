package com.aenlly.rcc.service;

import com.aenlly.rcc.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 服务类
 *
 * @author aenlly
 * @since 2021-12-11
 */
public interface IVideoService extends IService<Video> {

  /**
   * 查询置顶视频信息
   *
   * @return 视频信息
   */
  Video getByChekTop();
}
