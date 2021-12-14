package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.entity.VideoUserView;
import com.aenlly.rcc.mapper.VideoUserViewMapper;
import com.aenlly.rcc.service.IVideoUserViewService;
import com.aenlly.rcc.utils.QueryWrapperUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * VIEW 服务实现类
 *
 * @author aenlly
 * @since 2021-12-13
 */
@Service
public class VideoUserViewServiceImpl extends ServiceImpl<VideoUserViewMapper, VideoUserView>
    implements IVideoUserViewService {

  /**
   * 查询置顶视频信息
   *
   * @return 视频信息
   */
  @Override
  public VideoUserView getByChekTop() {
    Wrapper<VideoUserView> queryWrapper = QueryWrapperUtil.getByChekTop();
    return baseMapper.selectOne(queryWrapper);
  }

  /**
   * 根据公益视频标题查询集合
   *
   * @param title 标题
   * @return 结果集
   */
  @Override
  public List<VideoUserView> getByTitleList(String title) {
    Wrapper<VideoUserView> queryWrapper = QueryWrapperUtil.getVideoByTitleList(title);
    return baseMapper.selectList(queryWrapper);
  }

  /**
   * 通过视频编号，增加分享量
   *
   * @param id 视频编号
   * @return 是否成功
   */
  @Override
  public Boolean upShareCount(Long id) {
    VideoUserView view = baseMapper.selectById(id);
    view.setShareCount(view.getShareCount() + 1);
    return baseMapper.update(view, null) > 0;
  }
}
