package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.entity.Video;
import com.aenlly.rcc.enums.VideoCheckEnum;
import com.aenlly.rcc.eureka.service.IResourceUploadService;
import com.aenlly.rcc.mapper.VideoMapper;
import com.aenlly.rcc.service.ITmpFileService;
import com.aenlly.rcc.service.IVideoService;
import com.aenlly.rcc.utils.enums.QueryVideoTypeEnum;
import com.aenlly.rcc.utils.enums.UploadPathNameEnum;
import com.aenlly.rcc.utils.wrapper.VideoWrapperUtil;
import com.aenlly.rcc.vo.VideoVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 公益视频信息表 服务实现类

 @author aenlly
 @since 2022-01-11 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements IVideoService {

    /** 上传文件服务对象 */
    @Resource
    private IResourceUploadService uploadService;

    /** 临时存储表服务对象 */
    @Resource
    private ITmpFileService tmpFileService;

    /**
     查询视频信息集合

     @param page      分页对象
     @param queryType 查询字段类型
     @param text      查询字段内容
     @return 分页对象
     */
    @Override
    public IPage<VideoVo> getList(Page<VideoVo> page, QueryVideoTypeEnum queryType, String text) {
        Wrapper<VideoVo> wrapper = VideoWrapperUtil.queryVideoListPage(queryType, text);
        return baseMapper.getVideoInfo(page, wrapper);
    }

    /**
     根据编号修改视频状态

     @param id             编号
     @param videoCheckEnum 视频状态
     @return 是否成功
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {UnsupportedOperationException.class, RuntimeException.class})
    public Boolean updateByIdCheck(Long id, VideoCheckEnum videoCheckEnum) {
        Wrapper<Video> wrapper;
        // 如果是进行置顶操作的，那么需要多执行一步
        if (videoCheckEnum.equals(VideoCheckEnum.TOP)) {
            wrapper = VideoWrapperUtil.updateVideoByCheck(videoCheckEnum);
            int update = baseMapper.update(null, wrapper);
            if (update < 0) {
                throw new UnsupportedOperationException();
            }
        }
        wrapper = VideoWrapperUtil.updateVideoByIdPutCheck(id, videoCheckEnum);
        return this.update(wrapper);
    }

    /**
     上传视频文件

     @param file 文件
     @return 远程视频存储地址
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {UnsupportedOperationException.class, RuntimeException.class})
    public String uploadVideo(MultipartFile file) {
        return uploadService.uploadVideo(file, UploadPathNameEnum.VIDEO_VIDEO_NAME);
    }

    /**
     上传图片文件

     @param file 文件
     @return 远程图片存储地址
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {UnsupportedOperationException.class, RuntimeException.class})
    public String uploadImage(MultipartFile file) {
        return uploadService.uploadImage(file, UploadPathNameEnum.VIDEO_IMAGE_NAME);
    }

    /**
     添加信息到数据库中

     @param entity 添加实体
     @return 是否成功
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {UnsupportedOperationException.class, NullPointerException.class, RuntimeException.class})
    public Boolean create(Video entity) {
        if (entity.getVideoCheck()
                  .equals(VideoCheckEnum.TOP)) {
            Wrapper<Video> wrapper = VideoWrapperUtil.updateVideoByCheck(VideoCheckEnum.TOP);
            this.update(wrapper);
        }
        boolean save = this.save(entity);
        if (save) {
            return tmpFileService.updateBatchTmpInfo(entity.getVideoUrl(), entity.getVideoImg());
        }
        throw new NullPointerException();
    }

    /**
     更新信息到数据库中

     @param entity 更新实体
     @return 是否成功
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {UnsupportedOperationException.class, NullPointerException.class, RuntimeException.class})
    public Boolean update(Video entity) {
        if (entity.getVideoCheck()
                  .equals(VideoCheckEnum.TOP)) {
            Wrapper<Video> wrapper = VideoWrapperUtil.updateVideoByCheck(VideoCheckEnum.TOP);
            this.update(wrapper);
        }
        boolean b = this.updateById(entity);
        if (b) {
            return tmpFileService.updateBatchTmpInfo(entity.getVideoUrl(), entity.getVideoImg());
        }
        throw new NullPointerException();
    }
}
