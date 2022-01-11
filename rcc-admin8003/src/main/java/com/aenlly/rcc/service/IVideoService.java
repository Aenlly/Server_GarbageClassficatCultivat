package com.aenlly.rcc.service;

import com.aenlly.rcc.entity.Video;
import com.aenlly.rcc.enums.VideoCheckEnum;
import com.aenlly.rcc.utils.enums.QueryVideoType;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 公益视频信息表 服务类
 *
 * @author aenlly
 * @since 2022-01-11
 */
public interface IVideoService extends IService<Video> {
  /**
   * 查询视频信息集合
   *
   * @param page 分页对象
   * @param queryType 查询字段类型
   * @param text 查询字段内容
   * @return 分页对象
   */
  IPage<Video> getVideoList(Page<Video> page, QueryVideoType queryType, String text);

  /**
   * 根据编号修改视频状态
   *
   * @param id 编号
   * @param videoCheckEnum 视频状态
   * @return 是否成功
   */
  Boolean putVideoByIdCheck(Long id, VideoCheckEnum videoCheckEnum);
}
