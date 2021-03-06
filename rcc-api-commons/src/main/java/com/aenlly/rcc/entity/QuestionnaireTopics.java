package com.aenlly.rcc.entity;

import com.aenlly.rcc.enums.TopicStateEnum;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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

  /** 自增标识,无需填写 */
  @ApiModelProperty(value = "自增标识")
  @TableId(value = "id", type = IdType.AUTO)
  @JsonFormat(shape = JsonFormat.Shape.STRING) // 使其返回类型为string
  private Long id;

  /** 所属问卷名称,可不填 */
  @ApiModelProperty(value = "所属问卷名称")
  @TableField("questionnaire_name")
  private String questionnaireName;

  /** 所属问卷编号，必填 */
  @ApiModelProperty(value = "所属问卷编号")
  @TableField("questionnaire_id")
  private Long questionnaireId;

  /** 随机组卷批次号，必填 */
  @ApiModelProperty(value = "随机组卷批次号")
  @TableField("random_batch_index")
  private String randomBatchIndex;

  /** 所属题目名称，必填 */
  @ApiModelProperty(value = "所属题目名称")
  @TableField("belong_topic_name")
  private String belongTopicName;

  /** 所属题目id，必填 */
  @ApiModelProperty(value = "所属题目id")
  @TableField("belong_topic_id")
  private Long belongTopicId;

  @ApiModelProperty(value = "分值")
  @TableField("score")
  private Integer score;

  /** 所属题目id，创建时可不填 */
  @ApiModelProperty(value = "问卷题目状态，0未答，1已答")
  @TableField("state")
  private TopicStateEnum state;

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

  /** 选项信息 */
  @ApiModelProperty("选项表对象列表")
  @TableField(exist = false) // 非数据库字段
  private List<OptionsTable> optionsTables;

  /** 题目解析 */
  @ApiModelProperty("题目解析")
  @TableField(exist = false) // 非数据库字段
  private String analysis;
}
