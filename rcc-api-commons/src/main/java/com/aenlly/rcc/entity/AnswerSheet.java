package com.aenlly.rcc.entity;

import com.aenlly.rcc.enums.CorrectlyOrNotEnum;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 答卷-答案表
 *
 * @author aenlly
 * @since 2021-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AnswerSheet对象", description = "答卷-答案表")
public class AnswerSheet implements Serializable {

  private static final long serialVersionUID = 500847176346369175L;

  @ApiModelProperty(value = "自增标识")
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  @ApiModelProperty(value = "所选选项名称/所选答题内容")
  @TableField("selected_option_name")
  private String selectedOptionName;

  @ApiModelProperty(value = "所选选项id")
  @TableField("selected_option_id")
  private Integer selectedOptionId;

  @ApiModelProperty(value = "答题结果,正确/错误")
  @TableField("answer_results")
  private CorrectlyOrNotEnum answerResults;

  @ApiModelProperty(value = "得分")
  private String score;

  @ApiModelProperty(value = "所属题目名称")
  @TableField("belong_topic_name")
  private String belongTopicName;

  @ApiModelProperty(value = "所属题目id")
  @TableField("belong_topic_id")
  private Long belongTopicId;

  @ApiModelProperty(value = "所属答卷id")
  @TableField("belong_paper_tables")
  private Long belongPaperTables;

  @ApiModelProperty(value = "信息插入时间")
  @TableField(value = "create_time", fill = FieldFill.INSERT)
  private LocalDateTime createTime;

  @ApiModelProperty(value = "信息更新时间")
  @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updateTime;

  @ApiModelProperty(value = "逻辑删除,0未删除，1删除")
  @TableField("delete_flag")
  @TableLogic
  private Boolean deleteFlag;
}
