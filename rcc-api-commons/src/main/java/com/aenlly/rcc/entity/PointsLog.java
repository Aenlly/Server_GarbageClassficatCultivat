package com.aenlly.rcc.entity;

import com.aenlly.rcc.enums.PointsLogTypeEnum;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author aenlly
 * @since 2021-12-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "积分记录实体对象", description = "")
public class PointsLog implements Serializable {

  private static final long serialVersionUID = -4272680964553763349L;

  @ApiModelProperty(value = "自增编号")
  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  @ApiModelProperty(value = "积分记录描述")
  @TableField("log_desc")
  private String logDesc;

  @ApiModelProperty(value = "增加/消耗的积分")
  @TableField("number")
  private Integer number;

  @ApiModelProperty(value = "积分记录类型，1：增加/-1：减少")
  @TableField("type")
  private PointsLogTypeEnum type;

  @ApiModelProperty(value = "用户编号")
  @TableField("user_id")
  private String userId;

  @ApiModelProperty(value = "信息插入时间")
  @TableField("create_time")
  private LocalDateTime createTime;

  @ApiModelProperty(value = "信息更新时间")
  @TableField("update_time")
  private LocalDateTime updateTime;

  @ApiModelProperty(value = "逻辑删除,0未删除，1删除")
  @TableField("delete_flag")
  @TableLogic
  private Boolean deleteFlag;

  @ApiModelProperty(value = "版本号，用于乐观锁")
  @Version
  private Integer version;
}