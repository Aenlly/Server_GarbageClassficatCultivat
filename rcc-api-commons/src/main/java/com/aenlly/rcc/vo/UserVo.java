package com.aenlly.rcc.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author Aenlly
 * @create by date 2022/01/16 13:31
 * @projectName RefuseClassificationCultivate
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "用户信息列表对象", description = "用户信息与积分头衔表")
public class UserVo implements Serializable {

  private static final long serialVersionUID = 1382315921089272697L;

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
  private Integer accumulativePoints;

  @ApiModelProperty(value = "剩余积分")
  @TableField("remaining_points")
  private Integer remainingPoints;

  @ApiModelProperty(value = "答题总积分")
  @TableField("answer_points")
  private Integer answerPoints;

  @ApiModelProperty(value = "头衔id")
  @TableField("points_id")
  private Integer pointsId;

  @ApiModelProperty(value = "头衔名称")
  @TableField("points_name")
  private String pointsName;
}
