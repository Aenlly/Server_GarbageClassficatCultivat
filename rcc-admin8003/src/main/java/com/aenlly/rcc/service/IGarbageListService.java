package com.aenlly.rcc.service;

import com.aenlly.rcc.entity.GarbageList;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 所属垃圾类型信息条目表 服务类
 *
 * @author aenlly
 * @since 2022-01-15
 */
public interface IGarbageListService extends IService<GarbageList> {

  /**
   * 查询信息集合
   *
   * @param page 分页对象
   * @param belongId 所属垃圾类型id
   * @param text 查询内容
   * @return 分页对象
   */
  IPage<GarbageList> getList(Page<GarbageList> page, Integer belongId, String text);

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
  Boolean create(GarbageList entity);

  /**
   * 更新信息到数据库中
   *
   * @param entity 更新实体
   * @return 是否成功
   */
  Boolean update(GarbageList entity);
}
