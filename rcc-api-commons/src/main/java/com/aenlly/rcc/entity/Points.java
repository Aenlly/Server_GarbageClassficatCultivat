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
 * @author aenlly
 * @since 2021-12-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "积分头衔实体对象", description = "")
public class Points implements Serializable {

  private static final long serialVersionUID = -5348619879938974742L;

  @ApiModelProperty(value = "标识")
  @TableId(value = "points_id", type = IdType.AUTO)
  @JsonFormat(shape = JsonFormat.Shape.STRING) // 使其返回类型为string
  private Integer pointsId;

  @ApiModelProperty(value = "头衔名称")
  @TableField("points_name")
  private String pointsName;

  @ApiModelProperty(value = "所需积分")
  @TableField("points_require")
  private Integer pointsRequire;

  @ApiModelProperty(value = "信息创建时间")
  @TableField(value = "create_time", fill = FieldFill.INSERT)
  private LocalDateTime createTime;

  @ApiModelProperty(value = "信息更新时间")
  @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updateTime;

  @ApiModelProperty(value = "逻辑删除")
  @TableField("delete_flag")
  @TableLogic
  private Boolean deleteFlag;

  @ApiModelProperty(value = "版本号，用于乐观锁")
  @Version
  private Integer version;
}
