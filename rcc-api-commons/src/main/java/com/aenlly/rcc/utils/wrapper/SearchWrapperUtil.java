package com.aenlly.rcc.utils.wrapper;

import com.aenlly.rcc.entity.SearchLibrary;
import com.aenlly.rcc.entity.SearchUser;
import com.aenlly.rcc.enums.SearchTypeEnum;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * @author Aenlly
 * @create by date 2022/01/26 17:11
 * @projectName RefuseClassificationCultivate
 */
public class SearchWrapperUtil {

  /**
   * 根据查询条件获取操作对象 操作用户搜索记录
   *
   * @param typeEnum 查询的搜索类型
   * @return 查询对象
   */
  public static Wrapper<SearchUser> queryListPage(SearchTypeEnum typeEnum) {
    QueryWrapper<SearchUser> wrapper = new QueryWrapper<>();
    if (typeEnum != null) {
      wrapper.eq("type", typeEnum);
    }
    return wrapper;
  }

  /**
   * 根据查询条件获取操作对象，操作搜索信息库
   *
   * @param text 查询的搜索类型
   * @return 查询对象
   */
  public static Wrapper<SearchLibrary> queryListPage(String text) {
    QueryWrapper<SearchLibrary> wrapper = new QueryWrapper<>();
    wrapper.like("name", text);
    return wrapper;
  }
}
