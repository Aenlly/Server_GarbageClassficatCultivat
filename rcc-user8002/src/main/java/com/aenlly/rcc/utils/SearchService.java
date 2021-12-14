package com.aenlly.rcc.utils;

import com.aenlly.rcc.entity.GarbageLibrary;
import com.aenlly.rcc.entity.UserSearch;

import java.util.Collection;
import java.util.List;

/**
 * @author Aenlly
 * @create by date 2021/12/14 16:35
 * @projectName RefuseClassificationCultivate
 */
public interface SearchService {

  /**
   * 垃圾类型文本搜索
   *
   * @param name 垃圾名称
   * @return 搜索的垃圾所属类型集合
   */
  Collection<GarbageLibrary> searchText(String name, String userId);

  /**
   * 垃圾类型语音搜索
   *
   * @return 搜索的垃圾所属类型集合
   */
  Collection<GarbageLibrary> searchVoice();

  /**
   * 垃圾类型图像识别搜索
   *
   * @return 搜索的垃圾所属类型集合
   */
  Collection<GarbageLibrary> searchPicture();

  /**
   * 查询用户搜索记录
   *
   * @param userId 用户编号
   * @return 搜索记录列表
   */
  List<UserSearch> getSearchList(String userId);

  /**
   * 查询用户搜索记录
   *
   * @param userId 用户编号
   * @param name 搜索名称
   * @return 结果集
   */
  List<UserSearch> getSearchByName(String userId, String name);
}
