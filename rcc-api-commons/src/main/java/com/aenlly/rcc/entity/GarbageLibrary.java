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
 * @since 2021-12-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "GarbageLibrary对象", description = "")
public class GarbageLibrary implements Serializable {

  private static final long serialVersionUID = -2089535698007728855L;

  @ApiModelProperty(value = "编号")
  @TableId(value = "id", type = IdType.AUTO)
  @JsonFormat(shape = JsonFormat.Shape.STRING) // 使其返回类型为string
  private Integer id;

  @ApiModelProperty(value = "垃圾名称")
  private String name;

  @ApiModelProperty(value = "垃圾类型")
  private String type;

  @ApiModelProperty(value = "信息创建时间")
  @TableField(value = "create_time", fill = FieldFill.INSERT)
  private LocalDateTime createTime;

  @ApiModelProperty(value = "信息更新时间")
  @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updateTime;

  @ApiModelProperty(value = "版本号，用于乐观锁")
  @Version
  private Integer version;
}
