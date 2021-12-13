package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.entity.Video;
import com.aenlly.rcc.mapper.VideoMapper;
import com.aenlly.rcc.service.IVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author aenlly
 * @since 2021-12-11
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements IVideoService {

  /**
   * 查询置顶视频信息
   *
   * @return 视频信息
   */
  @Override
  public Video getByChekTop() {
    QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
    queryWrapper.select("video_url").eq("video_check", "置顶");
    return baseMapper.selectOne(queryWrapper);
  }
}
