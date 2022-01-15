package com.aenlly.rcc.admin.service;

import com.aenlly.rcc.entity.Garbage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 垃圾类型信息表 服务类
 *
 * @author aenlly
 * @since 2022-01-14
 */
public interface IGarbageService extends IService<Garbage> {

  /**
   * 查询信息集合
   *
   * @param page 分页对象
   * @return 分页对象
   */
  IPage<Garbage> getList(Page<Garbage> page);

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
   * 更新信息到数据库中
   *
   * @param entity 更新实体
   * @return 是否成功
   */
  Boolean update(Garbage entity);
}
