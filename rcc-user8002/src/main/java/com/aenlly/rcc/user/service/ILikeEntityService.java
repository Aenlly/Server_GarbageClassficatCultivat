package com.aenlly.rcc.user.service;

import com.aenlly.rcc.entity.LikeEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 服务类
 *
 * @author aenlly
 * @since 2021-12-12
 */
public interface ILikeEntityService extends IService<LikeEntity> {

  /**
   * 根据数据id与点赞实体统计点赞总数
   *
   * @param entityName 点赞的实体名称
   * @param dataId 数据id
   * @return 统计值
   */
  long getCountByDataId(String entityName, String dataId);

  /**
   * 根据用户id，数据id，实体名称查询是否点赞
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
}
