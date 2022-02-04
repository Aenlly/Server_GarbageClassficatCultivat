package com.aenlly.rcc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 题库表与题目表的多表 VIEW
 *
 * @author aenlly
 * @since 2022-02-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ItemPoolSubjectView对象", description = "题库表与题目表的多表 VIEW")
public class ItemPoolSubjectView implements Serializable {

  private static final long serialVersionUID = -8011426975684979836L;

  @ApiModelProperty(value = "自增标识")
  @JsonFormat(shape = JsonFormat.Shape.STRING) // 使其返回类型为string
  private Integer id;

  @ApiModelProperty(value = "题库名称")
  private String name;

  @ApiModelProperty(value = "题库描述")
  private String description;

  @ApiModelProperty(value = "题目总数")
  @JsonFormat(shape = JsonFormat.Shape.STRING) // 使其返回类型为string
  @TableField("subject_count")
  private Long subjectCount;
}
