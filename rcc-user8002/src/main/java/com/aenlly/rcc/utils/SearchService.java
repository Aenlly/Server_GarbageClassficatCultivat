package com.aenlly.rcc.utils;

import com.aenlly.rcc.entity.GarbageLibrary;

import java.util.Collection;

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
}
