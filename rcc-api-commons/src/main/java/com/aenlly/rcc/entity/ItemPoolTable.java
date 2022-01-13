package com.aenlly.rcc.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 题库表
 *
 * @author aenlly
 * @since 2021-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ItemPoolTable对象", description = "题库表")
public class ItemPoolTable implements Serializable {

  private static final long serialVersionUID = -841601414037852051L;

  @ApiModelProperty(value = "自增标识")
  @TableId(value = "id", type = IdType.AUTO)
  @JsonFormat(shape = JsonFormat.Shape.STRING) // 使其返回类型为string
  private Integer id;

  @ApiModelProperty(value = "题库名称")
  private String name;

  @ApiModelProperty(value = "题库描述")
  private String description;

  @ApiModelProperty(value = "信息插入时间")
  @TableField("create_time")
  private LocalDateTime createTime;

  @ApiModelProperty(value = "信息更新时间")
  @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updateTime;

  @ApiModelProperty(value = "逻辑删除,0未删除，1删除")
  @TableField("delete_flag")
  @TableLogic
  private Boolean deleteFlag;
}
