package com.aenlly.rcc.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 垃圾类型信息表
 *
 * @author aenlly
 * @since 2022-01-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties({"createTime", "updateTime", "deleteFlag", "version"}) // json序列传递前端时忽略
@ApiModel(value = "垃圾分类所属对象", description = "垃圾类型信息表")
public class Garbage implements Serializable {

  private static final long serialVersionUID = 6026538064958469171L;

  @ApiModelProperty(value = "自增标识")
  @TableId(value = "garbage_id", type = IdType.AUTO)
  @JsonFormat(shape = JsonFormat.Shape.STRING) // 使其返回类型为string
  private Integer garbageId;

  @ApiModelProperty(value = "垃圾宣传视频地址")
  @TableField("video_url")
  private String videoUrl;

  @ApiModelProperty(value = "垃圾类型")
  @TableField("garbage_type")
  private String garbageType;

  @ApiModelProperty(value = "垃圾类型中文名")
  @TableField("name_cn")
  private String nameCn;

  @ApiModelProperty(value = "垃圾类型英文名")
  @TableField("name_en")
  private String nameEn;

  @ApiModelProperty(value = "图标地址")
  @TableField("img_url")
  private String imgUrl;

  @ApiModelProperty(value = "垃圾类型标题")
  @TableField("text_title")
  private String textTitle;

  @ApiModelProperty(value = "垃圾类型描述")
  @TableField("text_desc")
  private String textDesc;

  @ApiModelProperty(value = "小程序垃圾类型背景颜色")
  @TableField("bg_color")
  private String bgColor;

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

  @ApiModelProperty(value = "垃圾分类所属详情列表")
  @TableField(exist = false) // 非数据库字段
  private List<GarbageList> garbageLists;
}
