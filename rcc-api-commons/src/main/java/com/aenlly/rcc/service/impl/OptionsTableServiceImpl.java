package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.entity.OptionsTable;
import com.aenlly.rcc.enums.CorrectlyOrNotEnum;
import com.aenlly.rcc.mapper.OptionsTableMapper;
import com.aenlly.rcc.service.IOptionsTableService;
import com.aenlly.rcc.utils.wrapper.OptionsTableWrapperUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  /**
   * 新增选项内容
   *
   * @param entity 新增实体
   * @return 是否成功，true成功，false代表选项超出数量，异常代表失败
   */
  @Override
  public Boolean create(OptionsTable entity) {
    // 获取数量
    List<OptionsTable> list = this.getList(entity.getBelongTopicId());
    // 判断是否选项超出
    if (list.size() < 4) {
      boolean save = this.save(entity);
      if (save) {
        return true;
      }
      throw new NullPointerException();
    } else {
      return false;
    }
  }

  /**
   * 选项是否正确答案
   *
   * @param entity 修改的实体
   * @return 是否成功
   */
  @Override
  @Transactional
  public Boolean updateOffOrOnById(OptionsTable entity) {
    if (entity.getCorrectlyOrNot() != CorrectlyOrNotEnum.CPRRECTLY) {
      return this.updateById(entity);
    } else {
      Wrapper<OptionsTable> wrapper =
          OptionsTableWrapperUtil.updateOffByBelongId(entity.getBelongTopicId());
      boolean update = this.update(wrapper);
      if (update) {
        return this.updateById(entity);
      }
    }
    throw new NullPointerException();
  }
}
