package com.aenlly.rcc.utils.wrapper;

import com.aenlly.rcc.entity.WasteTurnTreasure;
import com.aenlly.rcc.enums.AuditEnum;
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
   * @param tag 标签
   */
  public static Wrapper<WasteTurnTreasure> getListByTag(Integer tag) {
    QueryWrapper<WasteTurnTreasure> wrapper = new QueryWrapper<>();
    wrapper
        .select("id", "text", "text_desc", "img_url")
        .eq("text_tag", tag)
        .eq("audit", AuditEnum.THROUGH.getAudit());
    return wrapper;
  }

  /**
   * 根据标题 获取操作对象
   *
   * @param title 标题
   * @return 查询对象
   */
  public static Wrapper<WasteTurnTreasure> getListByTitle(String title) {
    QueryWrapper<WasteTurnTreasure> wrapper = new QueryWrapper<>();
    wrapper.select("id", "text").eq("audit", AuditEnum.THROUGH.getAudit()).like("text", title);
    return wrapper;
  }
}
