package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.entity.CollectEntity;
import com.aenlly.rcc.mapper.CollectEntityMapper;
import com.aenlly.rcc.service.ICollectEntityService;
import com.aenlly.rcc.utils.wrapper.QueryWrapperUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 服务实现类
 *
 * @author aenlly
 * @since 2021-12-13
 */
@Service
@Transactional
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
    Wrapper<CollectEntity> wrapper = QueryWrapperUtil.getCollectCountByDataId(entityName, dataId);
    return baseMapper.selectCount(wrapper);
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
    isParams(userId, entityName, dataId);
    Wrapper<CollectEntity> wrapper =
        QueryWrapperUtil.getIsCollectByUserId(userId, entityName, dataId);
    return baseMapper.selectCount(wrapper) > 0;
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
    isParams(userId, entityName, dataId);
    Wrapper<CollectEntity> wrapper =
        QueryWrapperUtil.getIsCollectByUserId(userId, entityName, dataId);
    return baseMapper.selectOne(wrapper).getCollectId();
  }

  /**
   * 用户服务-收藏根据条件进行查询
   *
   * @param userId 用户编号
   * @param name 搜索内容
   * @return 结果集合
   */
  @Override
  public List<CollectEntity> getListByUserId(String userId, String name) {
    isParams(userId, "1", "1");
    Wrapper<CollectEntity> wrapper = QueryWrapperUtil.getUserCollectListBy(userId, name, null);
    return baseMapper.selectList(wrapper);
  }

  /**
   * 用户服务-收藏根据条件进行查询
   *
   * @param userId 用户编号
   * @param name 搜索内容
   * @param entityName 实体名称
   * @return 结果集合
   */
  @Override
  public List<CollectEntity> getByUserIdAndEntityName(
      String userId, String name, String entityName) {
    isParams(userId, entityName, "1");
    Wrapper<CollectEntity> wrapper =
        QueryWrapperUtil.getUserCollectListBy(userId, name, entityName);
    return baseMapper.selectList(wrapper);
  }

  /**
   * 判断参数是否为空
   *
   * @param userId 用户编号
   * @param entityName 实体名称
   * @param dataId 数据id
   */
  public void isParams(String userId, String entityName, String dataId) {
    if (!StringUtils.isNotBlank(userId)
        || !StringUtils.isNotBlank(entityName)
        || !StringUtils.isNotBlank(dataId)) {
      throw new NullPointerException();
    }
  }
}
