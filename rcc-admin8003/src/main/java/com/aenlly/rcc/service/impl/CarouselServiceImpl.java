package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.entity.Carousel;
import com.aenlly.rcc.enums.CarouselShowFlagEnum;
import com.aenlly.rcc.eureka.service.IAdminUploadService;
import com.aenlly.rcc.mapper.CarouselMapper;
import com.aenlly.rcc.service.ICarouselService;
import com.aenlly.rcc.service.ITmpFileService;
import com.aenlly.rcc.utils.enums.UploadPathNameEnum;
import com.aenlly.rcc.utils.wrapper.CarouselWrapperUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 轮播图表 服务实现类
 *
 * @author aenlly
 * @since 2022-01-14
 */
@Service
public class CarouselServiceImpl extends ServiceImpl<CarouselMapper, Carousel>
    implements ICarouselService {

  /** 上传视频表请求服务对象 */
  @Resource private IAdminUploadService uploadService;

  /** 临时存储表服务对象 */
  @Resource private ITmpFileService tmpFileService;

  /**
   * 查询信息集合
   *
   * @param page 分页对象
   * @param text 查询内容
   * @return 分页对象
   */
  @Override
  public IPage<Carousel> getList(Page<Carousel> page, String text) {
    Wrapper<Carousel> wrapper = CarouselWrapperUtil.queryListPage(text);
    return baseMapper.selectPage(page, wrapper);
  }

  /**
   * 上线轮播信息 ArrayIndexOutOfBoundsException 超出可设置最大值时，抛出该异常
   *
   * @param id 编号
   * @param online 上线的枚举
   * @return 是否成功
   */
  @Override
  public Boolean putOnline(Long id, CarouselShowFlagEnum online) {
    Wrapper<Carousel> wrapper;
    // 如果是进行置顶操作的，那么需要多执行一步
    if (online.equals(CarouselShowFlagEnum.PUBLISH)) {
      wrapper = CarouselWrapperUtil.getOnlineCount(online);
      Long i = baseMapper.selectCount(wrapper);
      // 超出可设置最大数量轮播图
      if (i > 3) {
        throw new ArrayIndexOutOfBoundsException();
      }
    }
    wrapper = CarouselWrapperUtil.updateByIdPutState(id, online);
    return baseMapper.update(null, wrapper) > 0;
  }

  /**
   * 下线轮播信息
   *
   * @param id 编号
   * @param offline 下线的枚举
   * @return 是否成功
   */
  @Override
  public Boolean putOffline(Long id, CarouselShowFlagEnum offline) {
    Wrapper<Carousel> wrapper = CarouselWrapperUtil.updateByIdPutState(id, offline);
    return baseMapper.update(null, wrapper) > 0;
  }

  /**
   * 上传图片文件
   *
   * @param file 文件
   * @return 远程图片存储地址
   */
  @Override
  public String uploadImage(MultipartFile file) {
    return uploadService.uploadImage(file, UploadPathNameEnum.CAROUSEL_IMAGE_NAME);
  }

  /**
   * 添加信息到数据库中
   *
   * @param entity 添加实体
   * @return 是否成功
   */
  @Override
  public Boolean create(Carousel entity) {
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
  public Boolean update(Carousel entity) {
    int insert = baseMapper.updateById(entity);
    if (insert > 0) {
      return tmpFileService.updateBatchTmpInfo(entity.getImgUrl());
    }
    throw new NullPointerException();
  }
}
