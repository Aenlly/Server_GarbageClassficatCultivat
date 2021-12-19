package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.entity.WasteTurnTreasure;
import com.aenlly.rcc.enums.AuditEnum;
import com.aenlly.rcc.enums.WasteTagEnum;
import com.aenlly.rcc.mapper.WasteTurnTreasureMapper;
import com.aenlly.rcc.service.IWasteTurnTreasureService;
import com.aenlly.rcc.utils.wrapper.WasteWrapperUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 变废为宝表 服务实现类
 *
 * @author aenlly
 * @since 2021-12-18
 */
@Service
public class WasteTurnTreasureServiceImpl
    extends ServiceImpl<WasteTurnTreasureMapper, WasteTurnTreasure>
    implements IWasteTurnTreasureService {

  /**
   * 根据标签 获取变废为宝信息
   *
   * @param wasteTagEnum 标签枚举
   * @return 信息集合
   */
  @Override
  public List<WasteTurnTreasure> getListByTag(WasteTagEnum wasteTagEnum) {
    Wrapper<WasteTurnTreasure> wrapper =
        WasteWrapperUtil.getListByTag(null, wasteTagEnum, AuditEnum.THROUGH);
    return baseMapper.selectList(wrapper);
  }

  /**
   * 根据标题 搜索变废为宝信息 全局使用
   *
   * @param title 标题
   * @return 信息集合
   */
  @Override
  public List<WasteTurnTreasure> getListSearchByTitle(String title) {
    Wrapper<WasteTurnTreasure> wrapper =
        WasteWrapperUtil.getListByTitle(title, AuditEnum.THROUGH, null);
    return baseMapper.selectList(wrapper);
  }

  /**
   * 通过编号，增加分享量
   *
   * @param id 编号
   * @return 是否成功
   */
  @Override
  public Boolean upShareCount(Long id) {
    WasteTurnTreasure view = baseMapper.selectById(id);
    view.setShareCount(view.getShareCount() + 1);
    return baseMapper.updateById(view) > 0;
  }

  /**
   * 根据用户编号，审核状态 获取个人变废为宝上传信息
   *
   * @param userId 用户编号
   * @param auditEnum 审核状态
   * @return 信息集合
   */
  @Override
  public List<WasteTurnTreasure> getListByUserIdAndAudit(String userId, AuditEnum auditEnum) {
    Wrapper<WasteTurnTreasure> wrapper = WasteWrapperUtil.getListByTag(userId, null, auditEnum);
    return baseMapper.selectList(wrapper);
  }

  /**
   * 根据标题，用户班换 搜索变废为宝信息 用户个人使用
   *
   * @param title 标题
   * @param userId 用户编号
   * @return 信息集合
   */
  @Override
  public List<WasteTurnTreasure> getListSearchByUserIdAndTitle(String userId, String title) {
    Wrapper<WasteTurnTreasure> wrapper = WasteWrapperUtil.getListByTitle(title, null, userId);
    return baseMapper.selectList(wrapper);
  }

  /**
   * 根据用户id与实体Id删除
   *
   * @param userId 用户id
   * @param id 实体Id
   */
  @Override
  public Boolean removeByUserIdAndId(String userId, Long id) {
    Wrapper<WasteTurnTreasure> wrapper = WasteWrapperUtil.getDelByUserIdAndId(userId, id);
    return baseMapper.delete(wrapper) > 0;
  }
}
