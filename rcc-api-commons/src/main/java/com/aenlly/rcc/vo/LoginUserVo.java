package com.aenlly.rcc.vo;

import com.aenlly.rcc.entity.Points;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Aenlly
 * @create by date 2022/02/17 0:21
 * @projectName RefuseClassificationCultivate
 */
@Data
@AllArgsConstructor
public class LoginUserVo implements Serializable {

  private static final long serialVersionUID = 4049587316197351583L;

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

  @ApiModelProperty(value = "积分头衔对象")
  @TableField(exist = false) // 非数据库字段
  private Points points;

  @ApiModelProperty(value = "token")
  @TableField(exist = false) // 非数据库字段
  private String token;
}
