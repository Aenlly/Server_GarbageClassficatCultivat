package com.aenlly.rcc.mapper;

import com.aenlly.rcc.entity.Video;
import com.aenlly.rcc.vo.VideoVo;
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
 * @since 2022-01-11
 */
public interface VideoMapper extends BaseMapper<Video> {

  /**
   * 多表联查，公益视频表、收藏表、点赞表
   *
   * <p>${ew.customSqlSegment}让wrapper对象条件拼接
   *
   * @param page 分页对象
   * @param wrapper 实体封装操作条件
   * @return 查询内容
   */
  @Select(
      "select v.*,(select COUNT(1) from collect_entity c where c.data_id=v.video_id and c.entity_name=\"公益视频\") as "
          + "collect_count,(select COUNT(1) from like_entity l where l.data_id=v.video_id and l.entity_name=\"公益视频\") as "
          + "like_count  from Video v ${ew.customSqlSegment}")
  IPage<VideoVo> getVideoInfo(
      Page<VideoVo> page, @Param(Constants.WRAPPER) Wrapper<VideoVo> wrapper);
}
