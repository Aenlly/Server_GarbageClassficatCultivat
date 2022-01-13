package com.aenlly.rcc.entity;

import com.aenlly.rcc.enums.PointsLogTypeEnum;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author aenlly
 * @since 2021-12-16
 */
@Data
@Component
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "积分记录实体对象", description = "")
public class PointsLog implements Serializable {

  private static final long serialVersionUID = -4272680964553763349L;

  @ApiModelProperty(value = "自增编号")
  @TableId(value = "id", type = IdType.AUTO)
  @JsonFormat(shape = JsonFormat.Shape.STRING) // 使其返回类型为string
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

  @ApiModelProperty(value = "信息创建时间")
  @TableField(value = "create_time", fill = FieldFill.INSERT)
  private LocalDateTime createTime;

  @ApiModelProperty(value = "信息更新时间")
  @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updateTime;

  @ApiModelProperty(value = "逻辑删除,0未删除，1删除")
  @TableField("delete_flag")
  @TableLogic
  private Boolean deleteFlag;

  @ApiModelProperty(value = "版本号，用于乐观锁")
  @Version
  private Integer version;
}
