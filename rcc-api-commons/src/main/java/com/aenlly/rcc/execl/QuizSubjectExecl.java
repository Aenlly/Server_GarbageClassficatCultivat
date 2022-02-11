package com.aenlly.rcc.execl;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.io.Serializable;

/**
 * 题目导入格式实体
 *
 * @author Aenlly
 * @create by date 2022/02/05 22:59
 * @projectName RefuseClassificationCultivate
 */
@Data
public class QuizSubjectExecl implements Serializable {

  private static final long serialVersionUID = 3631833824229018674L;

  @Excel(name = "题目")
  private String subject;

  @Excel(name = "分值")
  private Integer score;

  @Excel(name = "题目解析")
  private String analysis;

  @Excel(
      name = "正确选项",
      replace = {"选项一_1", "选项二_2", "选项三_3", "选项四_4"},
      addressList = true)
  private Integer correctlyOptions;

  @Excel(name = "选项一")
  private String optionsA;

  @Excel(name = "选项二")
  private String optionsB;

  @Excel(name = "选项三")
  private String optionsC;

  @Excel(name = "选项四")
  private String optionsD;
}
