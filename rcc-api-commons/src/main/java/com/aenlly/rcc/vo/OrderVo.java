package com.aenlly.rcc.vo;

import com.aenlly.rcc.enums.OrderStateEnum;
import com.baomidou.mybatisplus.annotation.FieldFill;
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
 * @create by date 2022/01/17 16:23
 * @projectName RefuseClassificationCultivate
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "订单信息对象", description = "订单表与礼品表与礼品详情表")
public class OrderVo implements Serializable {

  private static final long serialVersionUID = -7300872268069858615L;

  @ApiModelProperty(value = "订单编号")
  @TableId("order_id")
  @JsonFormat(shape = JsonFormat.Shape.STRING) // 使其返回类型为string
  private Long orderId;

  @ApiModelProperty(value = "礼品id")
  @TableField("gift_id")
  @JsonFormat(shape = JsonFormat.Shape.STRING) // 使其返回类型为string
  private Long giftId;

  @ApiModelProperty(value = "用户编号")
  @TableField("user_id")
  private String userId;

  @ApiModelProperty(value = "礼品名称")
  @TableField("gift_name")
  private String giftName;

  @ApiModelProperty(value = "兑换码")
  private String code;

  @ApiModelProperty(value = "订单状态，-1代表未核销，1代表已核销")
  private OrderStateEnum state;

  @ApiModelProperty(value = "信息创建时间")
  @TableField(value = "create_time", fill = FieldFill.INSERT)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 输出的格式
  private LocalDateTime createTime;
}
