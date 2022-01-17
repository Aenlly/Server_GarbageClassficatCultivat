package com.aenlly.rcc.entity;

import com.aenlly.rcc.enums.GiftStateEnum;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 单个礼品信息条目表
 *
 * @author aenlly
 * @since 2021-12-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "GiftInfo对象", description = "单个礼品信息条目表")
public class GiftInfo implements Serializable {

  private static final long serialVersionUID = 5100700534973784401L;

  @ApiModelProperty(value = "标识字段")
  @TableId(value = "id", type = IdType.AUTO)
  @JsonFormat(shape = JsonFormat.Shape.STRING) // 使其返回类型为string
  private Long id;

  @ApiModelProperty(value = "礼品id")
  @TableField("gift_id")
  @JsonFormat(shape = JsonFormat.Shape.STRING) // 使其返回类型为string
  private Long giftId;

  @ApiModelProperty(value = "礼品状态，1代表未售出，0代表售出")
  private GiftStateEnum state;

  @ApiModelProperty(value = "兑换码")
  private String code;

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
