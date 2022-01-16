package com.aenlly.rcc.mapper;

import com.aenlly.rcc.entity.User;
import com.aenlly.rcc.vo.UserVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Select;

/**
 * 用户信息表 Mapper 接口
 *
 * @author aenlly
 * @since 2022-01-15
 */
public interface UserMapper extends BaseMapper<User> {

  @Select("select * from user left join points on user.points_id=points.points_id")
  IPage<UserVo> getUserPoints(Page<UserVo> page, Wrapper<UserVo> wrapper);
}
