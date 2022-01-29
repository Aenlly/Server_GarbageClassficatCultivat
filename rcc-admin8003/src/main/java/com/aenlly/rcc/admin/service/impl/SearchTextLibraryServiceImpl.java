package com.aenlly.rcc.admin.service.impl;

import com.aenlly.rcc.admin.service.ISearchTextLibraryService;
import com.aenlly.rcc.entity.SearchLibrary;
import com.aenlly.rcc.enums.GarbageTypeEnum;
import com.aenlly.rcc.mapper.SearchLibraryMapper;
import com.aenlly.rcc.utils.wrapper.SearchWrapperUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 搜索库信息服务实现类
 *
 * @author Aenlly
 * @create by date 2022/01/30 0:22
 * @projectName RefuseClassificationCultivate
 */
@Service
public class SearchTextLibraryServiceImpl extends ServiceImpl<SearchLibraryMapper, SearchLibrary>
    implements ISearchTextLibraryService {

  /**
   * 查询信息集合
   *
   * @param page 分页对象
   * @param text 查询内容
   * @return 分页对象
   */
  @Override
  public IPage<SearchLibrary> getList(Page<SearchLibrary> page, String text) {
    Wrapper<SearchLibrary> wrapper = SearchWrapperUtil.queryListPage(text);
    return this.page(page, wrapper);
  }

  /**
   * 获得垃圾分类类型
   *
   * @return 类型集合
   */
  @Override
  public GarbageTypeEnum[] getType() {
    return GarbageTypeEnum.values();
  }
}
