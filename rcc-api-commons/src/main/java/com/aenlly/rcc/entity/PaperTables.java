package com.aenlly.rcc.entity;

import com.aenlly.rcc.enums.SubmitStateEnum;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 答卷表
 *
 * @author aenlly
 * @since 2021-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PaperTables对象", description = "答卷表")
public class PaperTables implements Serializable {

  private static final long serialVersionUID = 6142965263961753751L;

  @ApiModelProperty(value = "自增标识")
  @TableId(value = "id", type = IdType.AUTO)
  @JsonFormat(shape = JsonFormat.Shape.STRING) // 使其返回类型为string
  private Long id;

  @ApiModelProperty(value = "所属问卷名称")
  @TableField("belong_questionnaire_name")
  private String belongQuestionnaireName;

  @ApiModelProperty(value = "所属问卷编号")
  @TableField("belong_questionnaire_id")
  private Long belongQuestionnaireId;

  @ApiModelProperty(value = "总得分")
  @TableField("total_score")
  private Integer totalScore;

  @ApiModelProperty(value = "答对数量")
  @TableField("right_amount")
  private Integer rightAmount;

  @ApiModelProperty(value = "答错数量")
  @TableField("error_amount")
  private Integer errorAmount;

  @ApiModelProperty(value = "未答数量")
  @TableField("un_answered_amount")
  private Integer unAnsweredAmount;

  @ApiModelProperty(value = "随机组卷批次号")
  @TableField("random_batch_index")
  private String randomBatchIndex;

  @ApiModelProperty(value = "答卷状态，0未提交，1已提交")
  @TableField("state")
  private SubmitStateEnum state;

  @ApiModelProperty(value = "答卷提交时间")
  @TableField("submit_time")
  private LocalDateTime submitTime;

  @ApiModelProperty(value = "用户编号")
  @TableField("user_id")
  private String userId;

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
