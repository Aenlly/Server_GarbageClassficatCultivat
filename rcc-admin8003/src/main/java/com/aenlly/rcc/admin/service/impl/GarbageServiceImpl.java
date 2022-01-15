package com.aenlly.rcc.admin.service.impl;

import com.aenlly.rcc.admin.service.IGarbageService;
import com.aenlly.rcc.entity.Garbage;
import com.aenlly.rcc.eureka.service.IResourceUploadService;
import com.aenlly.rcc.mapper.GarbageMapper;
import com.aenlly.rcc.service.ITmpFileService;
import com.aenlly.rcc.utils.enums.UploadPathNameEnum;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 垃圾类型信息表 服务实现类
 *
 * @author aenlly
 * @since 2022-01-14
 */
@Service
public class GarbageServiceImpl extends ServiceImpl<GarbageMapper, Garbage>
    implements IGarbageService {

  /** 上传文件服务对象 */
  @Resource private IResourceUploadService uploadService;

  /** 临时存储表服务对象 */
  @Resource private ITmpFileService tmpFileService;

  /**
   * 查询信息集合
   *
   * @param page 分页对象
   * @return 分页对象
   */
  @Override
  public IPage<Garbage> getList(Page<Garbage> page) {
    return baseMapper.selectPage(page, null);
  }

  /**
   * 上传视频文件
   *
   * @param file 文件
   * @return 远程视频存储地址
   */
  @Override
  public String uploadVideo(MultipartFile file) {
    return uploadService.uploadVideo(file, UploadPathNameEnum.GARBAGE_VIDEO_NAME);
  }

  /**
   * 上传图片文件
   *
   * @param file 文件
   * @return 远程图片存储地址
   */
  @Override
  public String uploadImage(MultipartFile file) {
    return uploadService.uploadImage(file, UploadPathNameEnum.GARBAGE_ICO_NAME);
  }

  /**
   * 更新信息到数据库中
   *
   * @param entity 更新实体
   * @return 是否成功
   */
  @Override
  public Boolean update(Garbage entity) {
    int insert = baseMapper.updateById(entity);
    if (insert > 0) {
      return tmpFileService.updateBatchTmpInfo(entity.getImgUrl());
    }
    throw new NullPointerException();
  }
}
