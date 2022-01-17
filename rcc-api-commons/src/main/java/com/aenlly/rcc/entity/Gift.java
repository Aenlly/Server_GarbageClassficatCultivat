package com.aenlly.rcc.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 礼品信息表
 *
 * @author aenlly
 * @since 2021-12-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Gift对象", description = "礼品信息表")
public class Gift implements Serializable {

  private static final long serialVersionUID = -5829242303562279347L;

  @ApiModelProperty(value = "礼品编号，自增")
  @TableId(value = "gift_id", type = IdType.ASSIGN_ID)
  @JsonFormat(shape = JsonFormat.Shape.STRING) // 使其返回类型为string
  private Long giftId;

  @ApiModelProperty(value = "礼品名称")
  @TableField("gift_name")
  private String giftName;

  @ApiModelProperty(value = "描述")
  @TableField("gift_desc")
  private String giftDesc;

  @ApiModelProperty(value = "礼品封面")
  @TableField("img_url")
  private String imgUrl;

  @ApiModelProperty(value = "所需积分")
  private Long point;

  @ApiModelProperty(value = "礼品价格")
  private BigDecimal price;

  @ApiModelProperty(value = "礼品类型")
  @TableField("type_id")
  private Integer typeId;

  @ApiModelProperty(value = "浏览量")
  @TableField("browse_count")
  private Long browseCount;

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
