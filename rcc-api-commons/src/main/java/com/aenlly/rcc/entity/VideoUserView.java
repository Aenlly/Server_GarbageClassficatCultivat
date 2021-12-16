package com.aenlly.rcc.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * VIEW
 *
 * @author aenlly
 * @since 2021-12-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "VideoUserView对象", description = "VIEW")
public class VideoUserView implements Serializable {

  private static final long serialVersionUID = 216547385307142585L;

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

  @ApiModelProperty(value = "视频状态,待发布，已发布，置顶")
  @TableField("video_check")
  private String videoCheck;

  @ApiModelProperty(value = "分享总数")
  @TableField("share_count")
  private Long shareCount;

  @ApiModelProperty(value = "版本号，用于乐观锁")
  @Version
  private Integer version;

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
}
