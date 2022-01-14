package com.aenlly.rcc.service;

import com.aenlly.rcc.entity.Video;
import com.aenlly.rcc.enums.VideoCheckEnum;
import com.aenlly.rcc.utils.enums.QueryVideoType;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 公益视频信息表 服务类
 *
 * @author aenlly
 * @since 2022-01-11
 */
public interface IVideoService extends IService<Video> {
  /**
   * 查询视频信息集合
   *
   * @param page 分页对象
   * @param queryType 查询字段类型
   * @param text 查询字段内容
   * @return 分页对象
   */
  IPage<Video> getList(Page<Video> page, QueryVideoType queryType, String text);

  /**
   * 根据编号修改视频状态
   *
   * @param id 编号
   * @param videoCheckEnum 视频状态
   * @return 是否成功
   */
  Boolean updateByIdCheck(Long id, VideoCheckEnum videoCheckEnum);

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
  Boolean create(Video entity);

  /**
   * 更新信息到数据库中
   *
   * @param entity 更新实体
   * @return 是否成功
   */
  Boolean update(Video entity);
}
