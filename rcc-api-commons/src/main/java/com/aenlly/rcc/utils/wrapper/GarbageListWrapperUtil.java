package com.aenlly.rcc.utils.wrapper;

import com.aenlly.rcc.entity.GarbageList;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * 所属垃圾类型条目信息表的实体封装操作对象获取工具类
 *
 * @author Aenlly
 * @create by date 2022/01/15 17:51
 * @projectName RefuseClassificationCultivate
 */
public class GarbageListWrapperUtil {

  /**
   * 根据条件获取实体封装操作对象
   *
   * @param belongId 所属垃圾类型id
   * @param text 查询内容
   * @return 查询对象
   */
  public static Wrapper<GarbageList> queryListPage(Integer belongId, String text) {
    QueryWrapper<GarbageList> wrapper = new QueryWrapper<>();
    wrapper.eq("garbage_id", belongId).like("garbage_name", text);
    return wrapper;
  }
}
