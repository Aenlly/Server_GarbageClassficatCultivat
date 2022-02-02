package com.aenlly.rcc.admin.service;

import com.aenlly.rcc.entity.HotInfo;
import com.aenlly.rcc.enums.HotInfoStateEnum;
import com.aenlly.rcc.utils.enums.QueryHotInfoTypeEnum;
import com.aenlly.rcc.vo.HotInfoVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 热门资讯表 服务类
 *
 * @author aenlly
 * @since 2022-02-03
 */
public interface IHotInfoService extends IService<HotInfo> {

  /**
   * 查询信息集合
   *
   * @param page 分页对象
   * @param queryType 查询类型
   * @param text 查询内容
   * @param state 信息状态，可为null
   * @return 分页对象
   */
  IPage<HotInfoVo> getList(
      Page<HotInfoVo> page, QueryHotInfoTypeEnum queryType, String text, HotInfoStateEnum state);

  /**
   * 根据编号修改资讯状态
   *
   * @param id 编号
   * @param state 资讯状态
   * @return 是否成功
   */
  Boolean updateByIdCheck(Long id, HotInfoStateEnum state);
}
