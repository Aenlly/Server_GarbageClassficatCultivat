package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.entity.Video;
import com.aenlly.rcc.enums.VideoCheckEnum;
import com.aenlly.rcc.eureka.service.IResourceService;
import com.aenlly.rcc.mapper.VideoMapper;
import com.aenlly.rcc.service.IVideoService;
import com.aenlly.rcc.utils.enums.QueryVideoType;
import com.aenlly.rcc.utils.wrapper.VideoWrapperUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 公益视频信息表 服务实现类
 *
 * @author aenlly
 * @since 2022-01-11
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements IVideoService {

  /** 上传视频表请求服务对象 */
  @Resource private IResourceService videoUploadService;

  /**
   * 查询视频信息集合
   *
   * @param page 分页对象
   * @param queryType 查询字段类型
   * @param text 查询字段内容
   * @return 分页对象
   */
  @Override
  public IPage<Video> getVideoList(Page<Video> page, QueryVideoType queryType, String text) {
    Wrapper<Video> wrapper = VideoWrapperUtil.queryVideoListPage(queryType, text);
    return baseMapper.selectPage(page, wrapper);
  }

  /**
   * 根据编号修改视频状态
   *
   * @param id 编号
   * @param videoCheckEnum 视频状态
   * @return 是否成功
   */
  @Override
  public Boolean putVideoByIdCheck(Long id, VideoCheckEnum videoCheckEnum) {
    Wrapper<Video> wrapper;
    // 如果是进行置顶操作的，那么需要多执行一步
    if (videoCheckEnum.equals(VideoCheckEnum.TOP)) {
      wrapper = VideoWrapperUtil.updateVideoByCheck(videoCheckEnum);
      int i = baseMapper.update(null, wrapper);
      if (i <= 0) {
        throw new UnsupportedOperationException();
      }
    }
    wrapper = VideoWrapperUtil.updateVideoByIdPutCheck(id, videoCheckEnum);
    return baseMapper.update(null, wrapper) > 0;
  }

  /**
   * 上传视频文件
   *
   * @param file 文件
   * @return 远程视频存储地址
   */
  @Override
  public String uploadVideo(MultipartFile file) {
    return videoUploadService.uploadVideo(file);
  }

  /**
   * 上传视频文件
   *
   * @param file 文件
   * @return 远程视频存储地址
   */
  @Override
  public String uploadImage(MultipartFile file) {
    return videoUploadService.uploadImage(file);
  }
}
