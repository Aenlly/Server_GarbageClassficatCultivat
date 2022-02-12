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

  /**
   * 新增选项内容
   *
   * @param entity 新增实体
   * @return 是否成功，true成功，false代表选项超出数量，异常代表失败
   */
  Boolean create(OptionsTable entity);

  /**
   * 选项是否正确答案
   *
   * @param entity 修改的实体
   * @return 是否成功
   */
  Boolean updateOffOrOnById(OptionsTable entity);
}
