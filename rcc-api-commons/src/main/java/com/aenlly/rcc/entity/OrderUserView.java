package com.aenlly.rcc.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2021-12-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "用户订单记录视图对象", description = "VIEW")
public class OrderUserView implements Serializable {

  private static final long serialVersionUID = 2693134616569639583L;

  @ApiModelProperty(value = "订单编号")
  @TableField("order_id")
  private Long orderId;

  @ApiModelProperty(value = "礼品名称")
  private String name;

  @ApiModelProperty(value = "兑换码")
  private String code;

  @ApiModelProperty(value = "用户编号")
  @TableField("user_id")
  private String userId;

  @ApiModelProperty(value = "订单状态，未核销，已核销")
  private String state;

  @ApiModelProperty(value = "信息插入时间")
  @TableField(value = "create_time", fill = FieldFill.INSERT)
  private LocalDateTime createTime;
}
