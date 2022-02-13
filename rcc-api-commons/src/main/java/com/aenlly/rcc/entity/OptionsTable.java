package com.aenlly.rcc.entity;

import com.aenlly.rcc.enums.CorrectlyOrNotEnum;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 选项表
 *
 * @author aenlly
 * @since 2021-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties({
  "belongTopicName",
  "createTime",
  "updateTime",
  "deleteFlag",
  "version"
}) // json序列传递前端时忽略
@ApiModel(value = "OptionsTable对象", description = "选项表")
public class OptionsTable implements Serializable {

  private static final long serialVersionUID = 5859330957243599088L;

  @ApiModelProperty(value = "自增标识")
  @TableId(value = "id", type = IdType.AUTO)
  @JsonFormat(shape = JsonFormat.Shape.STRING) // 使其返回类型为string
  private Long id;

  @ApiModelProperty(value = "选项名称")
  @TableField("option_name")
  private String optionName;

  @ApiModelProperty(value = "所属题目名称")
  @TableField("belong_topic_name")
  private String belongTopicName;

  @ApiModelProperty(value = "所属题目id")
  @TableField("belong_topic_id")
  @JsonFormat(shape = JsonFormat.Shape.STRING) // 使其返回类型为string
  private Long belongTopicId;

  @ApiModelProperty(value = "是否正确答案,1正确，-1错误")
  @TableField("correctly_or_not")
  private CorrectlyOrNotEnum correctlyOrNot;

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

  /**
   * 该构造方法用于导入时使用
   *
   * @param optionName 选项信息
   * @param belongTopicId 所属题目id
   * @param correctlyOrNot 是否正确答案
   */
  public OptionsTable(String optionName, Long belongTopicId, CorrectlyOrNotEnum correctlyOrNot) {
    this.optionName = optionName;
    this.belongTopicId = belongTopicId;
    this.correctlyOrNot = correctlyOrNot;
  }
}
