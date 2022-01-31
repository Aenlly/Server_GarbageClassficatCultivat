package com.aenlly.rcc.mapper;

import com.aenlly.rcc.entity.WasteTurnTreasure;
import com.aenlly.rcc.vo.WasteTurnTreasureVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 变废为宝表 Mapper 接口
 *
 * @author aenlly
 * @since 2021-12-18
 */
public interface WasteTurnTreasureMapper extends BaseMapper<WasteTurnTreasure> {

  /**
   * 多表联查，订单表、礼品条目表、礼品表
   *
   * <p>${ew.customSqlSegment}让wrapper对象条件拼接
   *
   * @param page 分页对象
   * @param wrapper 实体封装操作条件
   * @return 查询内容
   */
  @Select(
      "select w.*,(select COUNT(1) from collect_entity c where c.data_id=w.id and c.entity_name=\"变废为宝\") as collect_count,(select COUNT(1) from like_entity l where l.data_id=w.id and l.entity_name=\"变废为宝\") as like_count  from waste_turn_treasure w ${ew.customSqlSegment}")
  IPage<WasteTurnTreasureVo> getWasteInfo(
      Page<WasteTurnTreasureVo> page,
      @Param(Constants.WRAPPER) Wrapper<WasteTurnTreasureVo> wrapper);
}
