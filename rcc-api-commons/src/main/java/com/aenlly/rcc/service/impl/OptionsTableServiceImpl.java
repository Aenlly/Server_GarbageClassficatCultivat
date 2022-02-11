package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.entity.OptionsTable;
import com.aenlly.rcc.mapper.OptionsTableMapper;
import com.aenlly.rcc.service.IOptionsTableService;
import com.aenlly.rcc.utils.wrapper.OptionsTableWrapperUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 选项表 服务实现类
 *
 * @author aenlly
 * @since 2022-02-11
 */
@Service
public class OptionsTableServiceImpl extends ServiceImpl<OptionsTableMapper, OptionsTable>
    implements IOptionsTableService {
  /**
   * 查询信息集合
   *
   * @param belongId 所属题目id
   * @return 分页对象
   */
  @Override
  public List<OptionsTable> getList(Long belongId) {
    Wrapper<OptionsTable> wrapper = OptionsTableWrapperUtil.getOptionsByBelongId(belongId);
    return this.list(wrapper);
  }
}
