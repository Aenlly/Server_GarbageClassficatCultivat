package com.aenlly.rcc.mapper;

import com.aenlly.rcc.entity.SearchUser;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 首页用户搜索库表 Mapper 接口
 *
 * @author aenlly
 * @since 2022-01-26
 */
public interface SearchUserMapper extends BaseMapper<SearchUser> {

  /**
   * 获取图表数据
   *
   * <p>${ew.customSqlSegment}让wrapper对象条件拼接
   *
   * @param wrapper 实体封装操作条件
   * @return 查询内容
   */
  @Select(
      "select `search_user`.`name`,count(`search_user`.`name`) AS `y`,case `search_user`.`type` when 0 then '文字搜索' "
          + "when 1 then '语音搜索' else '图片识别' end as type from `search_user` ${ew.customSqlSegment}")
  <T> List<Map<String, T>> getChart(@Param(Constants.WRAPPER) Wrapper<T> wrapper);
}
