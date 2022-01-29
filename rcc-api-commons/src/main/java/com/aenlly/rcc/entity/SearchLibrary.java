package com.aenlly.rcc.entity;

import com.aenlly.rcc.enums.GarbageTypeEnum;
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
 * 文本垃圾分类搜索库表
 *
 * @author aenlly
 * @since 2022-01-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties({"updateTime", "deleteFlag", "version"}) // json序列传递前端时忽略
@ApiModel(value = "GarbageLibrary对象", description = "文本垃圾分类搜索库表")
public class SearchLibrary implements Serializable {

  private static final long serialVersionUID = -2089535698007728855L;

  @ApiModelProperty(value = "编号")
  @TableId(value = "id", type = IdType.AUTO)
  @JsonFormat(shape = JsonFormat.Shape.STRING) // 使其返回类型为string
  private Integer id;

  @ApiModelProperty(value = "垃圾名称")
  private String name;

  @ApiModelProperty(value = "垃圾类型，0：可回收垃圾，1：有害垃圾，2厨余垃圾，3其他垃圾")
  private GarbageTypeEnum type;

  @ApiModelProperty(value = "信息创建时间")
  @TableField(value = "create_time", fill = FieldFill.INSERT)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 输出的格式
  private LocalDateTime createTime;

  @ApiModelProperty(value = "信息更新时间")
  @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updateTime;

  @ApiModelProperty(value = "版本号，用于乐观锁")
  @Version
  private Integer version;
}
