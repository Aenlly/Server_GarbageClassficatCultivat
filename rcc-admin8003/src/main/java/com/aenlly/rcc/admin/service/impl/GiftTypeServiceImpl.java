package com.aenlly.rcc.admin.service.impl;

import com.aenlly.rcc.admin.service.IGiftTypeService;
import com.aenlly.rcc.entity.GiftType;
import com.aenlly.rcc.eureka.service.IResourceUploadService;
import com.aenlly.rcc.mapper.GiftTypeMapper;
import com.aenlly.rcc.service.ITmpFileService;
import com.aenlly.rcc.utils.enums.UploadPathNameEnum;
import com.aenlly.rcc.utils.wrapper.GiftTypeWrapperUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * 礼品类型表 服务实现类
 *
 * @author aenlly
 * @since 2022-01-17
 */
@Service
public class GiftTypeServiceImpl extends ServiceImpl<GiftTypeMapper, GiftType>
    implements IGiftTypeService {

  /** 上传文件服务对象 */
  @Resource private IResourceUploadService uploadService;

  /** 临时存储表服务对象 */
  @Resource private ITmpFileService tmpFileService;

  /**
   * 根据条件查询礼品类型信息
   *
   * @param text 类型名称
   * @return 信息集合
   */
  @Override
  public List<GiftType> getSelectListBy(String text) {
    Wrapper<GiftType> wrapper = GiftTypeWrapperUtil.queryByTypeName(text);
    return baseMapper.selectList(wrapper);
  }

  /**
   * 查询信息集合
   *
   * @param page 分页对象
   * @param text 查询内容
   * @return 分页对象
   */
  @Override
  public IPage<GiftType> getList(Page<GiftType> page, String text) {
    Wrapper<GiftType> wrapper = GiftTypeWrapperUtil.queryListPage(text);
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
    return uploadService.uploadImage(file, UploadPathNameEnum.GIFT_TYPE_IMAGE_NAME);
  }

  /**
   * 添加信息到数据库中
   *
   * @param entity 添加实体
   * @return 是否成功
   */
  @Override
  @Transactional
  public Boolean create(GiftType entity) {
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
  @Transactional
  public Boolean update(GiftType entity) {
    int insert = baseMapper.updateById(entity);
    if (insert > 0) {
      return tmpFileService.updateBatchTmpInfo(entity.getImgUrl());
    }
    throw new NullPointerException();
  }
}
