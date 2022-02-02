package com.aenlly.rcc.admin.service.impl;

import com.aenlly.rcc.admin.service.IHotInfoService;
import com.aenlly.rcc.entity.HotInfo;
import com.aenlly.rcc.enums.HotInfoStateEnum;
import com.aenlly.rcc.mapper.HotInfoMapper;
import com.aenlly.rcc.utils.enums.QueryHotInfoTypeEnum;
import com.aenlly.rcc.utils.wrapper.HotInfoWrapperUtil;
import com.aenlly.rcc.vo.HotInfoVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 热门资讯表 服务实现类
 *
 * @author aenlly
 * @since 2022-02-03
 */
@Service
public class HotInfoServiceImpl extends ServiceImpl<HotInfoMapper, HotInfo>
    implements IHotInfoService {
  /**
   * 查询信息集合
   *
   * @param page 分页对象
   * @param queryType 查询类型
   * @param text 查询内容
   * @param state 信息状态，可为null
   * @return 分页对象
   */
  @Override
  public IPage<HotInfoVo> getList(
      Page<HotInfoVo> page, QueryHotInfoTypeEnum queryType, String text, HotInfoStateEnum state) {
    Wrapper<HotInfoVo> wrapper = HotInfoWrapperUtil.queryListPage(queryType, text, state);
    return baseMapper.getHotInfo(page, wrapper);
  }

  /**
   * 根据编号修改资讯状态
   *
   * @param id 编号
   * @param state 资讯状态
   * @return 是否成功
   */
  @Override
  public Boolean updateByIdCheck(Long id, HotInfoStateEnum state) {
    Wrapper<HotInfo> wrapper = HotInfoWrapperUtil.updateStateById(id, state);
    return this.update(wrapper);
  }
}
