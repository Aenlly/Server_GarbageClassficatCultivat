package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.entity.LikeEntity;
import com.aenlly.rcc.mapper.LikeEntityMapper;
import com.aenlly.rcc.service.ILikeEntityService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author aenlly
 * @since 2021-12-12
 */
@Service
public class LikeEntityServiceImpl extends ServiceImpl<LikeEntityMapper, LikeEntity>
    implements ILikeEntityService {

  @Override
  public long getCountByDataId(String entityName, String dataId) {
    QueryWrapper<LikeEntity> queryWrapper =
        QueryWrapperUtil.getLikeCountByDataId(entityName, dataId);
    return baseMapper.selectCount(queryWrapper);
  }

  /**
   * 根据用户id，数据id，实体名称查询是否点赞
   *
   * @param userId 用户id
   * @param entityName 实体名称
   * @param dataId 数据id
   * @return 点赞信息
   */
  @Override
  public Boolean getIsByUserId(String userId, String entityName, String dataId) {
    if (StringUtils.isNotBlank(userId)
        && StringUtils.isNotBlank(entityName)
        && StringUtils.isNotBlank(dataId)) {
      QueryWrapper<LikeEntity> queryWrapper =
          QueryWrapperUtil.getIsLikeByUserId(userId, entityName, dataId);
      return baseMapper.selectCount(queryWrapper) > 0;
    }
    return false;
  }

  /**
   * 根据用户id，数据id，实体名称查询主键
   *
   * @param userId 用户id
   * @param entityName 实体名称
   * @param dataId 数据id
   * @return 主键标识
   */
  @Override
  public Integer getIdBy(String userId, String entityName, String dataId) {
    if (StringUtils.isNotBlank(userId)
        && StringUtils.isNotBlank(entityName)
        && StringUtils.isNotBlank(dataId)) {
      QueryWrapper<LikeEntity> queryWrapper =
          QueryWrapperUtil.getIsLikeByUserId(userId, entityName, dataId);
      return baseMapper.selectOne(queryWrapper).getLikeId();
    }
    return null;
  }
}
