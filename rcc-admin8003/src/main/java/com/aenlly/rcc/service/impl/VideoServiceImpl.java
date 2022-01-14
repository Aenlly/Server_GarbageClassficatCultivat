package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.entity.Video;
import com.aenlly.rcc.enums.VideoCheckEnum;
import com.aenlly.rcc.eureka.service.IAdminUploadService;
import com.aenlly.rcc.mapper.VideoMapper;
import com.aenlly.rcc.service.ITmpFileService;
import com.aenlly.rcc.service.IVideoService;
import com.aenlly.rcc.utils.enums.QueryVideoType;
import com.aenlly.rcc.utils.enums.UploadPathNameEnum;
import com.aenlly.rcc.utils.wrapper.VideoWrapperUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
  @Resource private IAdminUploadService uploadService;

  /** 临时存储表服务对象 */
  @Resource private ITmpFileService tmpFileService;

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
    return uploadService.uploadVideo(file, UploadPathNameEnum.VIDEO_VIDEO_NAME);
  }

  /**
   * 上传图片文件
   *
   * @param file 文件
   * @return 远程图片存储地址
   */
  @Override
  public String uploadImage(MultipartFile file) {
    return uploadService.uploadImage(file, UploadPathNameEnum.VIDEO_IMAGE_NAME);
  }

  /**
   * 添加视频信息到数据库中
   *
   * @param video 视频信息实体
   * @return 是否成功添加
   */
  @Override
  @Transactional
  public Boolean createVideo(Video video) {
    if (video.getVideoCheck().equals(VideoCheckEnum.TOP)) {
      Wrapper<Video> wrapper = VideoWrapperUtil.updateVideoByCheck(VideoCheckEnum.TOP);
      baseMapper.update(null, wrapper);
    }
    int insert = baseMapper.insert(video);
    if (insert > 0) {
      return tmpFileService.updateBatchTmpInfo(video.getVideoUrl(), video.getVideoImg());
    }
    throw new NullPointerException();
  }

  /**
   * 编辑公益视频信息到数据库中
   *
   * @param video 公益视频信息
   * @return 是否成功添加
   */
  @Override
  public Boolean updateVideo(Video video) {
    if (video.getVideoCheck().equals(VideoCheckEnum.TOP)) {
      Wrapper<Video> wrapper = VideoWrapperUtil.updateVideoByCheck(VideoCheckEnum.TOP);
      baseMapper.update(null, wrapper);
    }
    int insert = baseMapper.updateById(video);
    if (insert > 0) {
      return tmpFileService.updateBatchTmpInfo(video.getVideoUrl(), video.getVideoImg());
    }
    throw new NullPointerException();
  }
}
