package com.aenlly.rcc.service;

import com.aenlly.rcc.entity.OptionsTable;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 选项表 服务类
 *
 * @author aenlly
 * @since 2022-02-11
 */
public interface IOptionsTableService extends IService<OptionsTable> {

  /**
   * 查询信息集合
   *
   * @param belongId 所属题目id
   * @return 分页对象
   */
  List<OptionsTable> getList(Long belongId);
}
