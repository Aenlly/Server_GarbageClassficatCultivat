package com.aenlly.rcc.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 问卷表
 *
 * @author aenlly
 * @since 2021-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Questionnaire对象", description = "问卷表")
public class Questionnaire implements Serializable {

  private static final long serialVersionUID = 3809269060833037939L;

  @ApiModelProperty(value = "自增编号")
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  @ApiModelProperty(value = "问卷名称")
  @TableField("questionnaire_name")
  private String questionnaireName;

  @ApiModelProperty(value = "组卷类型，默认随机组卷，该字段扩充用")
  @TableField("group_form")
  private String groupForm;

  @ApiModelProperty(value = "单选题数量")
  @TableField("single_selection")
  private Integer singleSelection;

  @ApiModelProperty(value = "题库编号")
  @TableField("databank_id")
  private Integer databankId;

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
