package com.aenlly.rcc.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户信息表
 *
 * @author aenlly
 * @since 2021-12-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties({"createTime", "updateTime", "deleteFlag", "version"}) // json序列传递前端时忽略
@ApiModel(value = "User对象", description = "用户信息表")
public class User implements Serializable {

  private static final long serialVersionUID = -3678482271957347982L;

  @ApiModelProperty(value = "用户唯一标识")
  @TableId("user_id")
  private String userId;

  @ApiModelProperty(value = "用户昵称")
  @TableField("nick_name")
  private String nickName;

  @ApiModelProperty(value = "头像地址")
  @TableField("avatar_url")
  private String avatarUrl;

  @ApiModelProperty(value = "累计积分")
  @TableField("accumulative_points")
  private Long accumulativePoints;

  @ApiModelProperty(value = "剩余积分")
  @TableField("remaining_points")
  private Long remainingPoints;

  @ApiModelProperty(value = "答题总积分")
  @TableField("answer_points")
  private Long answerPoints;

  @ApiModelProperty(value = "头衔id")
  @TableField("points_id")
  private Integer pointsId;

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

  @ApiModelProperty(value = "积分头衔对象")
  @TableField(exist = false) // 非数据库字段
  private Points points;
}
