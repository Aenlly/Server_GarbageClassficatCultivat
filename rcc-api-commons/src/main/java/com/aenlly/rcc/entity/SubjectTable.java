package com.aenlly.rcc.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 题目表
 *
 * @author aenlly
 * @since 2021-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SubjectTable对象", description = "题目表")
public class SubjectTable implements Serializable {

  private static final long serialVersionUID = 3015727849094663149L;

  @ApiModelProperty(value = "自增编号")
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  @ApiModelProperty(value = "所属题库名称")
  @TableField("databank_name")
  private String databankName;

  @ApiModelProperty(value = "所属题库id")
  @TableField("databank_id")
  private Integer databankId;

  @ApiModelProperty(value = "题目名称")
  @TableField("topic_name")
  private String topicName;

  @ApiModelProperty(value = "分值")
  @TableField("score")
  private Integer score;

  @ApiModelProperty(value = "题目解析")
  @TableField("analysis")
  private String analysis;

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
