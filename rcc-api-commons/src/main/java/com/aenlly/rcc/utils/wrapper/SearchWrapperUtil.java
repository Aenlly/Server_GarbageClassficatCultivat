package com.aenlly.rcc.utils.wrapper;

import com.aenlly.rcc.entity.SearchLibrary;
import com.aenlly.rcc.entity.SearchUser;
import com.aenlly.rcc.enums.SearchTypeEnum;
import com.aenlly.rcc.utils.enums.QueryTimeTypeEnum;
import com.aenlly.rcc.vo.SearchNameChartVo;
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

  /**
   * 获得用户首页-文本搜索根据文本查询对象
   *
   * @param c 搜索文本
   * @return 查询对象
   */
  public static Wrapper<SearchLibrary> getSearchText(char c) {
    QueryWrapper<SearchLibrary> wrapper = new QueryWrapper<>();
    wrapper.like("name", String.valueOf(c));
    return wrapper;
  }

  /**
   * 用户首页-搜索记录，根据用户id获得操作对象
   *
   * @param userId 用户编号
   * @return 查询对象
   */
  public static Wrapper<SearchUser> getSearchList(String userId) {
    QueryWrapper<SearchUser> wrapper = new QueryWrapper<>();
    wrapper.select("name", "type").eq("user_id", userId).orderBy(true, false, "create_time");
    return wrapper;
  }

  /**
   * 用户首页-搜索记录，根据用户id与搜索垃圾名称获得操作对象
   *
   * @param userId 用户编号
   * @param name 搜索垃圾名称
   * @return 查询对象
   */
  public static Wrapper<SearchUser> getSearchByName(String userId, String name) {
    QueryWrapper<SearchUser> wrapper = new QueryWrapper<>();
    wrapper
        .select("name", "type")
        .eq("user_id", userId)
        .like("name", name)
        .orderBy(true, false, "create_time");
    return wrapper;
  }

  /**
   * 按照搜索名称分组获得操作对象
   *
   * @return 查询对象
   */
  public static Wrapper<SearchNameChartVo> getSearchNameChart() {
    QueryWrapper<SearchNameChartVo> wrapper = new QueryWrapper<>();
    wrapper
        .apply(QueryTimeTypeEnum.THIRTY_DAYS.getValue())
        .groupBy("name")
        .orderBy(true, false, "y")
        .last(" limit 10");
    return wrapper;
  }
}
