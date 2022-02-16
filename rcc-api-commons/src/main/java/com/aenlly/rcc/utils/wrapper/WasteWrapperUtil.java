package com.aenlly.rcc.utils.wrapper;

import com.aenlly.rcc.entity.WasteTurnTreasure;
import com.aenlly.rcc.enums.AuditEnum;
import com.aenlly.rcc.enums.WasteTagEnum;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * 获取变废为宝数据操作对象类
 *
 * @author Aenlly
 * @create by date 2021/12/18 21:26
 * @projectName RefuseClassificationCultivate
 */
public class WasteWrapperUtil {
  /**
   * 根据标签 获取操作对象
   *
   * @param userId 用户编号，根据需求赋值
   * @param tag 标签，根据需求赋值
   * @param audit 审核状态，必须值
   */
  public static Wrapper<WasteTurnTreasure> getListByTag(
      String userId, WasteTagEnum tag, AuditEnum audit) {
    QueryWrapper<WasteTurnTreasure> wrapper = new QueryWrapper<>();
    wrapper.select("id", "text", "text_desc", "img_url", "text_tag", "audit").eq("audit", audit);
    if (userId != null) {
      wrapper.eq("promulgator_id", userId);
    }
    if (tag != null) {
      wrapper.eq("text_tag", tag);
    }
    return wrapper;
  }

  /**
   * 根据标题 获取操作对象
   *
   * @param title 标题，必须值
   * @param audit 审核状态，根据需求赋值
   * @param userId 用户编号，根据需求赋值
   * @return 查询对象
   */
  public static Wrapper<WasteTurnTreasure> getListByTitle(
      String title, AuditEnum audit, String userId) {
    QueryWrapper<WasteTurnTreasure> wrapper = new QueryWrapper<>();
    wrapper.select("id", "text", "text_desc", "img_url", "text_tag", "audit").like("text", title);
    if (audit != null) {
      wrapper.eq("audit", audit);
    }
    if (userId != null) {
      wrapper.eq("promulgator_id", userId);
    }
    return wrapper;
  }

  /**
   * 根据条件 获取删除条件操作对象
   *
   * @param userId 用户编号
   * @param id 实体Id
   * @return 查询对象
   */
  public static Wrapper<WasteTurnTreasure> getDelByUserIdAndId(String userId, Long id) {
    QueryWrapper<WasteTurnTreasure> wrapper = new QueryWrapper<>();
    wrapper.eq("promulgator_id", userId).eq("id", id);
    return wrapper;
  }
}
