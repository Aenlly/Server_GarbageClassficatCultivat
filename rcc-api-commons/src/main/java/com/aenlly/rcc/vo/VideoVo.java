package com.aenlly.rcc.vo;

import com.aenlly.rcc.enums.VideoCheckEnum;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Aenlly
 * @create by date 2022/02/13 18:27
 * @projectName RefuseClassificationCultivate
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "公益视频信息列表对象", description = "公益视频与点赞/收藏表")
public class VideoVo implements Serializable {

  private static final long serialVersionUID = -211728457998747932L;

  @ApiModelProperty(value = "视频标识")
  @TableId(value = "video_id", type = IdType.ASSIGN_ID)
  @JsonFormat(shape = JsonFormat.Shape.STRING) // 使其返回类型为string
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
  private VideoCheckEnum videoCheck;

  @ApiModelProperty(value = "点赞总数")
  @TableField("like_count")
  @JsonFormat(shape = JsonFormat.Shape.STRING) // 使其返回类型为string,避免精度丢失
  private Long likeCount;

  @ApiModelProperty(value = "收藏总数")
  @TableField("collect_count")
  @JsonFormat(shape = JsonFormat.Shape.STRING) // 使其返回类型为string,避免精度丢失
  private Long collectCount;

  @ApiModelProperty(value = "分享总数")
  @TableField("share_count")
  @JsonFormat(shape = JsonFormat.Shape.STRING) // 使其返回类型为string,避免精度丢失
  private Long shareCount;

  @ApiModelProperty(value = "插入时间")
  @TableField("create_time")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 输出的格式
  private LocalDateTime createTime;
}
