package com.aenlly.rcc.utils;

/**
 * @author Aenlly
 * @create by date 2021/12/14 19:17
 * @projectName RefuseClassificationCultivate
 */
import com.aenlly.rcc.entity.GarbageLibrary;
import com.aenlly.rcc.service.IGarbageLibraryService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/** 使用线程增加本地垃圾分类库 垃圾分类库增加 */
@Component
public class GarbageLibraryAdd {

  @Resource private IGarbageLibraryService service;

  /**
   * 利用线程添加数据
   *
   * @param list 数据集合
   */
  public void garbageLibraryAdd(List<GarbageLibrary> list) {
    new Thread(
            () -> {
              service.saveBatch(list);
            })
        .start();
  }
}
