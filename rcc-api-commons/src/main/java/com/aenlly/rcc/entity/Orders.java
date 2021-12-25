package com.aenlly.rcc.entity;

import com.aenlly.rcc.enums.OrderStateEnum;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 订单记录表
 *
 * @author aenlly
 * @since 2021-12-25
 */
@Data
@Component
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Orders对象", description = "订单记录表")
public class Orders implements Serializable {

  private static final long serialVersionUID = 4614135421916421374L;

  @ApiModelProperty(value = "订单编号")
  @TableId(value = "order_id", type = IdType.ASSIGN_ID)
  private Long orderId;

  @ApiModelProperty(value = "礼品信息表编号")
  @TableField("gift_info_id")
  private Long giftInfoId;

  @ApiModelProperty(value = "用户编号")
  @TableField("user_id")
  private String userId;

  @ApiModelProperty(value = "订单状态，未核销，已核销")
  private OrderStateEnum state;

  @ApiModelProperty(value = "信息插入时间")
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
}
