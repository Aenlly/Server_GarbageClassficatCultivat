package com.aenlly.rcc.admin.service;

import com.aenlly.rcc.entity.WasteTurnTreasure;
import com.aenlly.rcc.enums.AuditEnum;
import com.aenlly.rcc.enums.IsUserUploadEnum;
import com.aenlly.rcc.enums.WasteTagEnum;
import com.aenlly.rcc.utils.enums.QueryWasteTypeEnum;
import com.aenlly.rcc.vo.WasteTurnTreasureVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 变废为宝表 服务类
 *
 * @author aenlly
 * @since 2022-01-31
 */
public interface IWasteTurnTreasureService extends IService<WasteTurnTreasure> {

  /**
   * 查询信息集合
   *
   * @param page 分页对象
   * @param queryType 查询类型
   * @param text 查询内容
   * @param textTag 订单状态，可为null
   * @param isUserUploadEnum 是否用户上传
   * @param auditEnums 审核状态集合
   * @return 分页对象
   */
  IPage<WasteTurnTreasureVo> getList(
      Page<WasteTurnTreasureVo> page,
      QueryWasteTypeEnum queryType,
      String text,
      WasteTagEnum textTag,
      IsUserUploadEnum isUserUploadEnum,
      List<AuditEnum> auditEnums);

  /**
   * 根据编号修改审核状态
   *
   * @param id 编号
   * @param audit 审核状态
   * @return 是否成功
   */
  Boolean updateByIdRevise(Long id, AuditEnum audit);

  /**
   * 上传视频文件
   *
   * @param file 文件
   * @return 远程视频存储地址
   */
  String uploadVideo(MultipartFile file);

  /**
   * 上传图片文件
   *
   * @param file 文件
   * @return 远程图片存储地址
   */
  String uploadImage(MultipartFile file);

  /**
   * 添加信息到数据库中
   *
   * @param entity 添加实体
   * @return 是否成功
   */
  Boolean create(WasteTurnTreasure entity);

  /**
   * 更新信息到数据库中
   *
   * @param entity 更新实体
   * @return 是否成功
   */
  Boolean update(WasteTurnTreasure entity);
}
