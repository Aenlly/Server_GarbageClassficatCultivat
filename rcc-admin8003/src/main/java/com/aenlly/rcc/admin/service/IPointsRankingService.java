package com.aenlly.rcc.admin.service;

import com.aenlly.rcc.entity.PointsLog;
import com.aenlly.rcc.utils.enums.QueryPointsTypeEnum;
import com.aenlly.rcc.vo.UserVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 积分排名查看 服务类
 *
 * @author Aenlly
 * @create by date 2022/02/14 23:32
 * @projectName RefuseClassificationCultivate
 */
public interface IPointsRankingService {

  /**
   * 查询用户积分信息分页集合
   *
   * @param page 分页对象
   * @param text 查询内容
   * @param typeEnum 积分排序类型
   * @return 分页对象
   */
  IPage<UserVo> getList(Page<UserVo> page, String text, QueryPointsTypeEnum typeEnum);

  /**
   * 查询积分记录信息集合
   *
   * @param page 分页对象
   * @param userId 用户id
   * @return 分页对象
   */
  IPage<PointsLog> getPointsListById(Page<PointsLog> page, String userId);
}
