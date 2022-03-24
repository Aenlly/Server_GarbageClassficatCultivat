package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.service.IGiftInfoService;
import com.aenlly.rcc.service.IGiftListViewService;
import com.aenlly.rcc.service.IGiftService;
import com.aenlly.rcc.entity.Gift;
import com.aenlly.rcc.entity.GiftInfo;
import com.aenlly.rcc.entity.GiftListView;
import com.aenlly.rcc.eureka.service.IResourceUploadService;
import com.aenlly.rcc.mapper.GiftMapper;
import com.aenlly.rcc.service.ITmpFileService;
import com.aenlly.rcc.utils.enums.QueryGiftTypeEnum;
import com.aenlly.rcc.utils.enums.UploadPathNameEnum;
import com.aenlly.rcc.utils.wrapper.GiftWrapperUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 礼品信息表 服务实现类
 *
 * @author aenlly
 * @since 2022-01-17
 */
@Service
public class GiftServiceImpl extends ServiceImpl<GiftMapper, Gift> implements IGiftService {

  @Resource private IGiftInfoService giftInfoService;

  @Resource private IGiftListViewService giftListViewService;

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
   * @param typeId 类型编号，可为null
   * @return 分页对象
   */
  @Override
  public IPage<GiftListView> getList(
      Page<GiftListView> page, QueryGiftTypeEnum queryType, String text, Long typeId) {
    return giftListViewService.getList(page, queryType, text, typeId);
  }

  /**
   * 上传图片文件
   *
   * @param file 文件
   * @return 远程图片存储地址
   */
  @Override
  public String uploadImage(MultipartFile file) {
    return uploadService.uploadImage(file, UploadPathNameEnum.GIFT_IMAGE_NAME);
  }

  /**
   * 添加信息到数据库中
   *
   * @param entity 添加实体
   * @return 是否成功
   */
  @Override
  @Transactional
  public Boolean create(GiftListView entity) {

    Gift gift = setGift(entity);

    int insert = baseMapper.insert(gift);

    if (insert > 0) {
      GiftInfo giftInfo = new GiftInfo();
      giftInfo.setGiftId(gift.getGiftId());
      List<GiftInfo> list = new ArrayList<>();
      int number = 0;
      do {
        list.add(giftInfo);
        number++;
      } while (number < entity.getNumber());
      giftInfoService.saveBatch(list);
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
  public Boolean update(GiftListView entity) {
    Gift gift = setGift(entity);

    int insert = baseMapper.updateById(gift);

    if (insert > 0) {
      return tmpFileService.updateBatchTmpInfo(entity.getImgUrl());
    }
    throw new NullPointerException();
  }

  /**
   * 增加礼品的库存值
   *
   * @param id 礼品编号
   * @param number 增加的数量
   * @return 是否成功
   */
  @Override
  @Transactional
  public Boolean addNumber(Long id, Long number) {
    GiftInfo giftInfo = new GiftInfo();
    giftInfo.setGiftId(id);
    List<GiftInfo> list = new ArrayList<>();
    int i = 0;
    do {
      list.add(giftInfo);
      i++;
    } while (i < number);
    return giftInfoService.saveBatch(list);
  }

  /**
   * 减少礼品的库存值
   *
   * @param id 礼品编号
   * @param number 增加的数量
   * @return 是否成功
   */
  @Override
  @Transactional
  public Boolean cutNumber(Long id, Long number) {
    GiftListView gift = giftListViewService.getById(id);
    if (gift.getNumber() < number) {
      throw new IndexOutOfBoundsException();
    } else {
      Wrapper<GiftInfo> wrapper = GiftWrapperUtil.delByGiftId(id, number);
      List<GiftInfo> list = giftInfoService.list(wrapper);
      return giftInfoService.removeByIds(list);
    }
  }

  /**
   * 设置创建或更新的礼品参数
   *
   * @param entity 多表实体
   * @return 礼品信息实体
   */
  private Gift setGift(GiftListView entity) {
    Gift gift = new Gift();
    gift.setGiftId(entity.getGiftId());
    gift.setGiftDesc(entity.getGiftDesc());
    gift.setGiftName(entity.getGiftName());
    gift.setImgUrl(entity.getImgUrl());
    gift.setPoint(entity.getPoint());
    gift.setPrice(entity.getPrice());
    gift.setTypeId(entity.getTypeId());
    return gift;
  }
}
