package com.aenlly.rcc.admin.service;

import com.aenlly.rcc.entity.GiftType;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 礼品类型表 服务类
 *
 * @author aenlly
 * @since 2022-01-17
 */
public interface IGiftTypeService extends IService<GiftType> {

  /**
   * 根据条件查询礼品类型信息
   *
   * @param text 类型名称
   * @return 信息集合
   */
  List<GiftType> getSelectListBy(String text);

  /**
   * 查询信息集合
   *
   * @param page 分页对象
   * @param text 查询内容
   * @return 分页对象
   */
  IPage<GiftType> getList(Page<GiftType> page, String text);

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
  Boolean create(GiftType entity);

  /**
   * 更新信息到数据库中
   *
   * @param entity 更新实体
   * @return 是否成功
   */
  Boolean update(GiftType entity);
}
