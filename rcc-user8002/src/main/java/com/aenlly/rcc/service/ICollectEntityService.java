package com.aenlly.rcc.service;

import com.aenlly.rcc.entity.CollectEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 服务类
 *
 * @author aenlly
 * @since 2021-12-13
 */
public interface ICollectEntityService extends IService<CollectEntity> {

  /**
   * 根据数据id与收藏实体统计点赞总数
   *
   * @param entityName 收藏的实体名称
   * @param dataId 数据id
   * @return 统计值
   */
  long getCountByDataId(String entityName, String dataId);

  /**
   * 根据用户id，数据id，实体名称查询是否收藏
   *
   * @param userId 用户id
   * @param entityName 实体名称
   * @param dataId 数据id
   * @return 是否点赞
   */
  Boolean getIsByUserId(String userId, String entityName, String dataId);

  /**
   * 根据用户id，数据id，实体名称查询主键
   *
   * @param userId 用户id
   * @param entityName 实体名称
   * @param dataId 数据id
   * @return 主键标识
   */
  Integer getIdBy(String userId, String entityName, String dataId);

  /**
   * 用户服务-收藏根据条件进行查询
   *
   * @param userId 用户编号
   * @param name 搜索内容
   * @return 结果集合
   */
  List<CollectEntity> getListByUserId(String userId, String name);
}
