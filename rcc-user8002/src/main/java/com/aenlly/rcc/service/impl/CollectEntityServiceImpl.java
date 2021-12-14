package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.entity.CollectEntity;
import com.aenlly.rcc.mapper.CollectEntityMapper;
import com.aenlly.rcc.service.ICollectEntityService;
import com.aenlly.rcc.utils.QueryWrapperUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author aenlly
 * @since 2021-12-13
 */
@Service
public class CollectEntityServiceImpl extends ServiceImpl<CollectEntityMapper, CollectEntity>
    implements ICollectEntityService {

  /**
   * 根据数据id与收藏实体统计点赞总数
   *
   * @param entityName 收藏的实体名称
   * @param dataId 数据id
   * @return 统计值
   */
  @Override
  public long getCountByDataId(String entityName, String dataId) {
    Wrapper<CollectEntity> queryWrapper =
        QueryWrapperUtil.getCollectCountByDataId(entityName, dataId);
    return baseMapper.selectCount(queryWrapper);
  }

  /**
   * 根据用户id，数据id，实体名称查询是否收藏
   *
   * @param userId 用户id
   * @param entityName 实体名称
   * @param dataId 数据id
   * @return 是否点赞
   */
  @Override
  public Boolean getIsByUserId(String userId, String entityName, String dataId) {
    if (StringUtils.isNotBlank(userId)
        && StringUtils.isNotBlank(entityName)
        && StringUtils.isNotBlank(dataId)) {
      Wrapper<CollectEntity> queryWrapper =
          QueryWrapperUtil.getIsCollectByUserId(userId, entityName, dataId);
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
      Wrapper<CollectEntity> queryWrapper =
          QueryWrapperUtil.getIsCollectByUserId(userId, entityName, dataId);
      return baseMapper.selectOne(queryWrapper).getCollectId();
    }
    return null;
  }
}
