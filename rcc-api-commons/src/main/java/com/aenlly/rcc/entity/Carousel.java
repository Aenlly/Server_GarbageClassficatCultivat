package com.aenlly.rcc.entity;

import com.aenlly.rcc.enums.CarouselShowFlagEnum;
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
 * 轮播图表
 *
 * @author aenlly
 * @since 2022-01-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties({"updateTime", "deleteFlag", "version"}) // json序列传递前端时忽略
@ApiModel(value = "Carousel对象", description = "轮播图表")
public class Carousel implements Serializable {

  private static final long serialVersionUID = -2207491841443682850L;

  @ApiModelProperty(value = "标识字段")
  @TableId(value = "carousel_id", type = IdType.AUTO)
  @JsonFormat(shape = JsonFormat.Shape.STRING) // 使其返回类型为string
  private Integer carouselId;

  @ApiModelProperty(value = "轮播名称")
  private String name;

  @ApiModelProperty(value = "轮播图片")
  @TableField("img_url")
  private String imgUrl;

  @ApiModelProperty(value = "跳转地址")
  @TableField("activity_url")
  private String activityUrl;

  @ApiModelProperty(value = "上线/下线状态，0下线，1上线")
  @TableField("show_flag")
  private CarouselShowFlagEnum showFlag;

  @ApiModelProperty(value = "创建时间")
  @TableField(value = "create_time", fill = FieldFill.INSERT)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 输出的格式
  private LocalDateTime createTime;

  @ApiModelProperty(value = "更新时间")
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
