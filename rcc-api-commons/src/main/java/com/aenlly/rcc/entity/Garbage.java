package com.aenlly.rcc.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author aenlly
 * @since 2021-12-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "垃圾分类所属对象", description = "")
public class Garbage implements Serializable {

  private static final long serialVersionUID = 6026538064958469171L;

  @ApiModelProperty(value = "自增标识")
  @TableId(value = "garbage_id", type = IdType.AUTO)
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

  @ApiModelProperty(value = "垃圾分类所属详情列表")
  @TableField(exist = false) // 非数据库字段
  private List<GarbageList> garbageLists;
}
