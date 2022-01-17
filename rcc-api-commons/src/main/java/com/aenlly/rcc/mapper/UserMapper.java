package com.aenlly.rcc.mapper;

import com.aenlly.rcc.entity.User;
import com.aenlly.rcc.vo.UserVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 用户信息表 Mapper 接口
 *
 * @author aenlly
 * @since 2022-01-15
 */
public interface UserMapper extends BaseMapper<User> {

  /**
   * 多表联查，用户信息表、积分头衔表
   *
   * <p>${ew.customSqlSegment}让wrapper对象条件拼接
   *
   * @param page 分页对象
   * @param wrapper 实体封装操作条件
   * @return 查询内容
   */
  @Select(
      "select * from user left join points on user.points_id=points.points_id ${ew.customSqlSegment}")
  IPage<UserVo> getUserPoints(Page<UserVo> page, @Param(Constants.WRAPPER) Wrapper<UserVo> wrapper);
}
