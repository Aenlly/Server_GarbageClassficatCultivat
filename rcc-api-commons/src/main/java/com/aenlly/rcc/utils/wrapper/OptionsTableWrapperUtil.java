package com.aenlly.rcc.utils.wrapper;

import com.aenlly.rcc.entity.OptionsTable;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * 选项信息的实体封装操作对象获取工具类
 *
 * @author Aenlly
 * @create by date 2022/02/12 0:43
 * @projectName RefuseClassificationCultivate
 */
public class OptionsTableWrapperUtil {

  /**
   * 根据所属题目id获取操作对象
   *
   * @param belongId 所属题目id
   * @return 查询对象
   */
  public static Wrapper<OptionsTable> getOptionsByBelongId(Long belongId) {
    QueryWrapper<OptionsTable> wrapper = new QueryWrapper<>();
    wrapper.eq("belong_topic_id", belongId);
    return wrapper;
  }
}
