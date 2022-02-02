package com.aenlly.rcc.mapper;

import com.aenlly.rcc.entity.HotInfo;
import com.aenlly.rcc.vo.HotInfoVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Mapper 接口
 *
 * @author aenlly
 * @since 2021-12-12
 */
public interface HotInfoMapper extends BaseMapper<HotInfo> {

  /**
   * 多表联查，热门资讯、收藏表、点赞表
   *
   * <p>${ew.customSqlSegment}让wrapper对象条件拼接
   *
   * @param page 分页对象
   * @param wrapper 实体封装操作条件
   * @return 查询内容
   */
  @Select(
      "select h.*,(select COUNT(1) from collect_entity c where c.data_id=h.hot_info_id and c.entity_name=\"热门资讯\") as "
          + "collect_count,(select COUNT(1) from like_entity l where l.data_id=h.hot_info_id and l"
          + ".entity_name=\"热门资讯\") as like_count  from hot_info h ${ew.customSqlSegment}")
  IPage<HotInfoVo> getHotInfo(
      Page<HotInfoVo> page, @Param(Constants.WRAPPER) Wrapper<HotInfoVo> wrapper);
}
