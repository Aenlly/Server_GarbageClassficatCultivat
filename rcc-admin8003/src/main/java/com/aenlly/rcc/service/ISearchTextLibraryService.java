package com.aenlly.rcc.service;

import com.aenlly.rcc.entity.SearchLibrary;
import com.aenlly.rcc.enums.GarbageTypeEnum;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 搜索信息库 服务类
 *
 * @author Aenlly
 * @create by date 2022/01/30 0:21
 * @projectName RefuseClassificationCultivate
 */
public interface ISearchTextLibraryService extends IService<SearchLibrary> {

  /**
   * 查询信息集合
   *
   * @param page 分页对象
   * @param text 查询内容
   * @return 分页对象
   */
  IPage<SearchLibrary> getList(Page<SearchLibrary> page, String text);

  /**
   * 获得垃圾分类类型
   *
   * @return 类型数组
   */
  GarbageTypeEnum[] getType();
}
