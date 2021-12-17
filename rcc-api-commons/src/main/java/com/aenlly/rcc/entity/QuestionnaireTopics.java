package com.aenlly.rcc.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 问卷题目对应表
 *
 * @author aenlly
 * @since 2021-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "QuestionnaireTopics对象", description = "问卷题目对应表")
public class QuestionnaireTopics implements Serializable {
    
    private static final long serialVersionUID = -3461832238982139772L;
    @ApiModelProperty(value = "自增标识")
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  @ApiModelProperty(value = "所属问卷名称")
  @TableField("questionnaire_name")
  private String questionnaireName;

  @ApiModelProperty(value = "所属问卷编号")
  @TableField("questionnaire_id")
  private Long questionnaireId;

  @ApiModelProperty(value = "随机组卷批次号")
  @TableField("random_batch_index")
  private String randomBatchIndex;

  @ApiModelProperty(value = "所属题目名称")
  @TableField("belong_topic")
  private String belongTopic;

  @ApiModelProperty(value = "所属题目id")
  @TableField("belong_topic_id")
  private Long belongTopicId;

  @ApiModelProperty(value = "问卷题目状态，0未答，1已答")
  @TableField("state")
  private Boolean state;

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
