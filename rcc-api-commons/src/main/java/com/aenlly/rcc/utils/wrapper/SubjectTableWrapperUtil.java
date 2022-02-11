package com.aenlly.rcc.utils.wrapper;

import com.aenlly.rcc.entity.SubjectTable;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * 题目信息的实体封装操作对象获取工具类
 *
 * @author Aenlly
 * @create by date 2022/02/11 19:26
 * @projectName RefuseClassificationCultivate
 */
public class SubjectTableWrapperUtil {

  /**
   * 根据查询条件获取操作对象
   *
   * @param belongId 所属题库id
   * @param text 查询内容
   * @return 查询对象
   */
  public static Wrapper<SubjectTable> queryListPage(Long belongId, String text) {
    QueryWrapper<SubjectTable> wrapper = new QueryWrapper<>();
    wrapper.eq("databank_id", belongId).like("topic_name", text);
    return wrapper;
  }
}
