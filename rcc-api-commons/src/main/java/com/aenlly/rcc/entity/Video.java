package com.aenlly.rcc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
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
@ApiModel(value = "公益视频对象", description = "")
public class Video implements Serializable {

  private static final long serialVersionUID = 9198257600849363799L;

  @ApiModelProperty(value = "视频标识")
  @TableId("video_id")
  private Long videoId;

  @ApiModelProperty(value = "视频标题")
  @TableField("video_title")
  private String videoTitle;

  @ApiModelProperty(value = "视频地址")
  @TableField("video_url")
  private String videoUrl;

  @ApiModelProperty(value = "视频封面")
  @TableField("video_img")
  private String videoImg;

  @ApiModelProperty(value = "视频描述")
  @TableField("video_desc")
  private String videoDesc;

  @ApiModelProperty(value = "奖励积分")
  @TableField("video_points")
  private String videoPoints;

  @ApiModelProperty(value = "视频状态")
  @TableField("video_check")
  private String videoCheck;

  @ApiModelProperty(value = "插入时间")
  @TableField("create_time")
  private LocalDateTime createTime;

  @ApiModelProperty(value = "更新时间")
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