package com.aenlly.rcc.service.impl;

import cn.hutool.json.JSONUtil;
import com.aenlly.rcc.entity.AdminTable;
import com.aenlly.rcc.entity.WasteTurnTreasure;
import com.aenlly.rcc.enums.AuditEnum;
import com.aenlly.rcc.enums.IsUserUploadEnum;
import com.aenlly.rcc.enums.WasteTagEnum;
import com.aenlly.rcc.eureka.service.IResourceUploadService;
import com.aenlly.rcc.mapper.WasteTurnTreasureMapper;
import com.aenlly.rcc.service.ITmpFileService;
import com.aenlly.rcc.service.IWasteTurnTreasureService;
import com.aenlly.rcc.utils.JWTUtil;
import com.aenlly.rcc.utils.enums.QueryWasteTypeEnum;
import com.aenlly.rcc.utils.enums.UploadPathNameEnum;
import com.aenlly.rcc.utils.wrapper.WasteTurnTreasureWrapperUtil;
import com.aenlly.rcc.vo.WasteTurnTreasureVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * 变废为宝表 服务实现类
 *
 * @author aenlly
 * @since 2022-01-31
 */
@Service
public class WasteTurnTreasureServiceImpl
    extends ServiceImpl<WasteTurnTreasureMapper, WasteTurnTreasure>
    implements IWasteTurnTreasureService {

  /** 上传文件服务对象 */
  @Resource private IResourceUploadService uploadService;

  /** 临时存储表服务对象 */
  @Resource private ITmpFileService tmpFileService;

  /**
   * 查询信息集合
   *
   * @param page 分页对象
   * @param queryType 查询类型
   * @param text 查询内容
   * @param textTag 标签，可为null
   * @param isUserUploadEnum 是否用户上传
   * @param auditEnums 审核状态集合
   * @return 分页对象
   */
  @Override
  public IPage<WasteTurnTreasureVo> getList(
      Page<WasteTurnTreasureVo> page,
      QueryWasteTypeEnum queryType,
      String text,
      WasteTagEnum textTag,
      IsUserUploadEnum isUserUploadEnum,
      List<AuditEnum> auditEnums) {

    Wrapper<WasteTurnTreasureVo> wrapper =
        WasteTurnTreasureWrapperUtil.queryListPage(
            queryType, text, textTag, isUserUploadEnum, auditEnums);

    return baseMapper.getWasteInfo(page, wrapper);
  }

  /**
   * 根据编号修改审核状态
   *
   * @param id 编号
   * @param audit 审核状态
   * @return 是否成功
   */
  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {UnsupportedOperationException.class, NullPointerException.class, RuntimeException.class})
  public Boolean updateByIdRevise(Long id, AuditEnum audit) {
    Wrapper<WasteTurnTreasure> wrapper = WasteTurnTreasureWrapperUtil.updateByIdPutAudit(id, audit);
    return this.update(wrapper);
  }

  /**
   * 上传视频文件
   *
   * @param file 文件
   * @return 远程视频存储地址
   */
  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {UnsupportedOperationException.class, NullPointerException.class, RuntimeException.class})
  public String uploadVideo(MultipartFile file) {
    return uploadService.uploadVideo(file, UploadPathNameEnum.WASTE_ADMIN_FILE_NAME);
  }

  /**
   * 上传图片文件
   *
   * @param file 文件
   * @return 远程图片存储地址
   */
  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {UnsupportedOperationException.class, NullPointerException.class, RuntimeException.class})
  public String uploadImage(MultipartFile file) {
    return uploadService.uploadImage(file, UploadPathNameEnum.WASTE_ADMIN_IMAGE_NAME);
  }

  /**
   * 添加信息到数据库中
   *
   * @param entity 添加实体
   * @return 是否成功
   */
  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {UnsupportedOperationException.class, NullPointerException.class, RuntimeException.class})
  public Boolean create(WasteTurnTreasure entity) {
    Claims claims = JWTUtil.parseJWT(entity.getPromulgatorId());
    String json = claims.getSubject();
    AdminTable adminTable = JSONUtil.toBean(json, AdminTable.class);
    entity.setPromulgatorId(adminTable.getId().toString());
    entity.setIsUserUpload(IsUserUploadEnum.NO);
    entity.setAudit(AuditEnum.THROUGH);
    boolean save = this.save(entity);
    if (save) {
      return tmpFileService.updateBatchTmpInfo(entity.getVideoUrl(), entity.getImgUrl());
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
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {UnsupportedOperationException.class, NullPointerException.class, RuntimeException.class})
  public Boolean update(WasteTurnTreasure entity) {
    boolean b = this.updateById(entity);
    if (b) {
      return tmpFileService.updateBatchTmpInfo(entity.getVideoUrl(), entity.getImgUrl());
    }
    throw new NullPointerException();
  }
}
