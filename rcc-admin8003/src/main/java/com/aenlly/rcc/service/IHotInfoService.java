package com.aenlly.rcc.service;

import com.aenlly.rcc.entity.HotInfo;
import com.aenlly.rcc.enums.HotInfoStateEnum;
import com.aenlly.rcc.utils.enums.QueryHotInfoTypeEnum;
import com.aenlly.rcc.vo.HotInfoVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 热门资讯表 服务类
 *
 * @author aenlly
 * @since 2022-02-03
 */
public interface IHotInfoService extends IService<HotInfo> {

  /**
   * 查询信息集合
   *
   * @param page 分页对象
   * @param queryType 查询类型
   * @param text 查询内容
   * @param state 信息状态，可为null
   * @return 分页对象
   */
  IPage<HotInfoVo> getList(
      Page<HotInfoVo> page, QueryHotInfoTypeEnum queryType, String text, HotInfoStateEnum state);

  /**
   * 根据编号修改资讯状态
   *
   * @param id 编号
   * @param state 资讯状态
   * @return 是否成功
   */
  Boolean updateByIdCheck(Long id, HotInfoStateEnum state);

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
  Boolean create(HotInfo entity);

  /**
   * 更新信息到数据库中
   *
   * @param entity 更新实体
   * @return 是否成功
   */
  Boolean update(HotInfo entity);
}
