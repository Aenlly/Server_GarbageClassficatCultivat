package com.aenlly.rcc.user.service;

import com.aenlly.rcc.entity.WasteTurnTreasure;
import com.aenlly.rcc.enums.AuditEnum;
import com.aenlly.rcc.enums.WasteTagEnum;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 变废为宝表 服务类
 *
 * @author aenlly
 * @since 2021-12-18
 */
public interface IWasteTurnTreasureService extends IService<WasteTurnTreasure> {

  /**
   * 根据标签 获取变废为宝信息
   *
   * @param wasteTagEnum 标签枚举
   * @return 信息集合
   */
  List<WasteTurnTreasure> queryListByTag(WasteTagEnum wasteTagEnum);

  /**
   * 根据标题 获取变废为宝信息
   *
   * @param title 标题
   * @return 信息集合
   */
  List<WasteTurnTreasure> queryListSearchByTitle(String title);

  /**
   * 通过编号，增加分享量
   *
   * @param id 编号
   * @return 是否成功
   */
  Boolean updateShareCount(Long id);

  /**
   * 根据用户编号，审核状态 获取个人变废为宝上传信息
   *
   * @param userId 用户编号
   * @param auditEnum 审核状态
   * @return 信息集合
   */
  List<WasteTurnTreasure> queryListByUserIdAndAudit(String userId, AuditEnum auditEnum);

  /**
   * 根据标题，用户班换 搜索变废为宝信息 用户个人使用
   *
   * @param title 标题
   * @param userId 用户编号
   * @return 信息集合
   */
  List<WasteTurnTreasure> queryListSearchByUserIdAndTitle(String userId, String title);

  /**
   * 根据用户id与实体Id删除
   *
   * @param userId 用户id
   * @param id 实体Id
   */
  Boolean removeByUserIdAndId(String userId, Long id);

  /**
   * 根据实体插入信息
   *
   * @param wasteTurnTreasure 变废为宝实体
   * @return 是否成功
   */
  Boolean createUserWasteInfo(WasteTurnTreasure wasteTurnTreasure);

  /**
   * 根据实体插入信息
   *
   * @param wasteTurnTreasure 变废为宝实体
   * @return 是否成功
   */
  Boolean updateUserWasteInfo(WasteTurnTreasure wasteTurnTreasure);
}
