package com.aenlly.rcc.admin.service;

import com.aenlly.rcc.entity.Gift;
import com.aenlly.rcc.entity.GiftListView;
import com.aenlly.rcc.utils.enums.QueryGiftTypeEnum;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 礼品信息表 服务类
 *
 * @author aenlly
 * @since 2022-01-17
 */
public interface IGiftService extends IService<Gift> {

  /**
   * 查询信息集合
   *
   * @param page 分页对象
   * @param queryType 查询类型
   * @param text 查询内容
   * @param typeId 类型编号，可为null
   * @return 分页对象
   */
  IPage<GiftListView> getList(
      Page<GiftListView> page, QueryGiftTypeEnum queryType, String text, Long typeId);

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
  Boolean create(GiftListView entity);

  /**
   * 更新信息到数据库中
   *
   * @param entity 更新实体
   * @return 是否成功
   */
  Boolean update(GiftListView entity);

  /**
   * 增加礼品的库存值
   *
   * @param id 礼品编号
   * @param number 增加的数量
   * @return 是否成功
   */
  Boolean addNumber(Long id, Long number);

  /**
   * 减少礼品的库存值
   *
   * @param id 礼品编号
   * @param number 增加的数量
   * @return 是否成功
   */
  Boolean cutNumber(Long id, Long number);
}
