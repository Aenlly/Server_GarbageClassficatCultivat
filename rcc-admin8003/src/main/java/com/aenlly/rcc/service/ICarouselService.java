package com.aenlly.rcc.service;

import com.aenlly.rcc.entity.Carousel;
import com.aenlly.rcc.enums.CarouselShowFlagEnum;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 轮播图表 服务类
 *
 * @author aenlly
 * @since 2022-01-14
 */
public interface ICarouselService extends IService<Carousel> {

  /**
   * 查询信息集合
   *
   * @param page 分页对象
   * @param text 查询内容
   * @return 分页对象
   */
  IPage<Carousel> getList(Page<Carousel> page, String text);

  /**
   * 上线轮播信息
   *
   * @param id 编号
   * @param online 上线的枚举
   * @return 是否成功
   */
  Boolean putOnline(Long id, CarouselShowFlagEnum online);

  /**
   * 下线轮播信息
   *
   * @param id 编号
   * @param offline 下线的枚举
   * @return 是否成功
   */
  Boolean putOffline(Long id, CarouselShowFlagEnum offline);

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
  Boolean create(Carousel entity);

  /**
   * 更新信息到数据库中
   *
   * @param entity 更新实体
   * @return 是否成功
   */
  Boolean update(Carousel entity);
}
