package com.aenlly.rcc.utils.wrapper;

import com.aenlly.rcc.entity.AnswerSheet;
import com.aenlly.rcc.entity.PaperTables;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;

/**
 * 答卷信息管理(答卷表、答卷题目表)封装操作对象获取工具类
 *
 * @author Aenlly
 * @create by date 2022/02/13 15:22
 * @projectName RefuseClassificationCultivate
 */
public class QuestionSheetWrapperUtil {

  /**
   * 根据条件获取答卷表实体操作对象
   *
   * @param belongId 所属问卷id
   * @param startTime 开始时间
   * @param endTime 结束时间
   * @return 查询对象
   */
  public static Wrapper<PaperTables> queryListPage(
      Long belongId, String startTime, String endTime) {
    QueryWrapper<PaperTables> wrapper = new QueryWrapper<>();
    wrapper.eq("belong_questionnaire_id", belongId);
    if (StringUtils.isNotBlank(startTime)) {
      wrapper.ge("submit_time", startTime);
    }
    if (StringUtils.isNotBlank(endTime)) {
      wrapper.le("submit_time", endTime);
    }
    return wrapper;
  }

  /**
   * 根据所属答卷id条件获取答卷-答案表实体操作对象
   *
   * @param belongPaperId 所属答卷id
   * @return 查询对象
   */
  public static Wrapper<AnswerSheet> queryListByBelongPaperId(Long belongPaperId) {
    QueryWrapper<AnswerSheet> wrapper = new QueryWrapper<>();
    wrapper.eq("belong_paper_tables", belongPaperId);
    return wrapper;
  }
}
