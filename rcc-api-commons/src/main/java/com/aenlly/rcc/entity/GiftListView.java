package com.aenlly.rcc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 礼品信息表与礼品条目表 VIEW
 *
 * @author aenlly
 * @since 2021-12-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties({"typeId", "deleteFlag", "version"}) // json序列传递前端时忽略
@ApiModel(value = "礼品信息视图GiftListView对象", description = "礼品信息表与礼品条目表 联合视图")
public class GiftListView implements Serializable {

  private static final long serialVersionUID = -1469883932683444730L;

  @ApiModelProperty(value = "礼品编号，自增")
  @TableId("gift_id")
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
  @JsonFormat(shape = JsonFormat.Shape.STRING) // 使其返回类型为string
  private Long point;

  @ApiModelProperty(value = "礼品价格")
  private BigDecimal price;

  @ApiModelProperty(value = "浏览量")
  @TableField("browse_count")
  @JsonFormat(shape = JsonFormat.Shape.STRING) // 使其返回类型为string
  private Long browseCount;

  @ApiModelProperty(value = "库存数量")
  @TableField("number")
  @JsonFormat(shape = JsonFormat.Shape.STRING) // 使其返回类型为string
  private Long number;

  @ApiModelProperty(value = "已售数量")
  @TableField("sold")
  @JsonFormat(shape = JsonFormat.Shape.STRING) // 使其返回类型为string
  private Long sold;

  @ApiModelProperty(value = "类型编号")
  @TableField("type_id")
  @JsonFormat(shape = JsonFormat.Shape.STRING) // 使其返回类型为string
  private Integer typeId;

  @ApiModelProperty(value = "类型名称")
  @TableField("type_name")
  private String typeName;

  @ApiModelProperty(value = "逻辑删除,0未删除，1删除")
  @TableField("delete_flag")
  @TableLogic
  private Boolean deleteFlag;

  @ApiModelProperty(value = "版本号，用于乐观锁")
  @Version
  private Integer version;
}
