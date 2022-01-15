package com.aenlly.rcc.admin.service.impl;

import com.aenlly.rcc.admin.service.IGarbageListService;
import com.aenlly.rcc.entity.GarbageList;
import com.aenlly.rcc.eureka.service.IResourceUploadService;
import com.aenlly.rcc.mapper.GarbageListMapper;
import com.aenlly.rcc.service.ITmpFileService;
import com.aenlly.rcc.utils.enums.UploadPathNameEnum;
import com.aenlly.rcc.utils.wrapper.GarbageListWrapperUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 所属垃圾类型信息条目表 服务实现类
 *
 * @author aenlly
 * @since 2022-01-15
 */
@Service
public class GarbageListServiceImpl extends ServiceImpl<GarbageListMapper, GarbageList>
    implements IGarbageListService {

  /** 上传文件服务对象 */
  @Resource private IResourceUploadService uploadService;

  /** 临时存储表服务对象 */
  @Resource private ITmpFileService tmpFileService;

  /**
   * 查询信息集合
   *
   * @param page 分页对象
   * @param belongId 所属垃圾类型id
   * @param text 查询内容
   * @return 分页对象
   */
  @Override
  public IPage<GarbageList> getList(Page<GarbageList> page, Integer belongId, String text) {
    Wrapper<GarbageList> wrapper = GarbageListWrapperUtil.queryListPage(belongId, text);
    return baseMapper.selectPage(page, wrapper);
  }

  /**
   * 上传图片文件
   *
   * @param file 文件
   * @return 远程图片存储地址
   */
  @Override
  public String uploadImage(MultipartFile file) {
    return uploadService.uploadImage(file, UploadPathNameEnum.GARBAGE_IMAGE_NAME);
  }

  /**
   * 添加信息到数据库中
   *
   * @param entity 添加实体
   * @return 是否成功
   */
  @Override
  public Boolean create(GarbageList entity) {
    int insert = baseMapper.insert(entity);
    if (insert > 0) {
      return tmpFileService.updateBatchTmpInfo(entity.getImgUrl());
    }
    throw new NullPointerException();
  }

  /**
   * 更新信息到数据库中
   *
   * @param entity 更新实体
   * @return 是否成功
   */
  @Override
  public Boolean update(GarbageList entity) {
    int insert = baseMapper.updateById(entity);
    if (insert > 0) {
      return tmpFileService.updateBatchTmpInfo(entity.getImgUrl());
    }
    throw new NullPointerException();
  }
}
