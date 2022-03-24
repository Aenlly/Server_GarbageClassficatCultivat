package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.service.IPointsLogService;
import com.aenlly.rcc.service.IPointsRankingService;
import com.aenlly.rcc.service.IUserService;
import com.aenlly.rcc.entity.PointsLog;
import com.aenlly.rcc.utils.enums.QueryPointsTypeEnum;
import com.aenlly.rcc.utils.wrapper.UserWrapperUtil;
import com.aenlly.rcc.vo.UserVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 积分排名查看 服务实现类
 *
 * @author Aenlly
 * @create by date 2022/02/14 23:32
 * @projectName RefuseClassificationCultivate
 */
@Service
public class PointsRankingServiceImpl implements IPointsRankingService {
  /** 用户信息服务对象 */
  @Resource private IUserService userService;
  /** 积分记录服务对象 */
  @Resource private IPointsLogService pointsLogService;
  /**
   * 查询用户积分信息分页集合
   *
   * @param page 分页对象
   * @param text 查询内容
   * @param typeEnum 积分排序类型
   * @return 分页对象
   */
  @Override
  public IPage<UserVo> getList(Page<UserVo> page, String text, QueryPointsTypeEnum typeEnum) {
    return userService.getList(page, text, typeEnum);
  }

  /**
   * 查询积分记录信息集合
   *
   * @param page 分页对象
   * @param userId 用户id
   * @return 分页对象
   */
  @Override
  public IPage<PointsLog> getPointsListById(Page<PointsLog> page, String userId) {
    Wrapper<PointsLog> wrapper = UserWrapperUtil.getPointsListById(userId);
    return pointsLogService.page(page, wrapper);
  }
}
