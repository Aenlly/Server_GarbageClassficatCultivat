package com.aenlly.rcc.entity;

import com.aenlly.rcc.enums.GiftTypeShowEnum;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 礼品类型表
 *
 * @author aenlly
 * @since 2021-12-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties({"updateTime", "deleteFlag", "version"}) // json序列传递前端时忽略
@ApiModel(value = "礼品类型实体对象", description = "礼品类型表")
public class GiftType implements Serializable {

  private static final long serialVersionUID = -3000496852084582776L;

  @ApiModelProperty(value = "自增标识")
  @TableId(value = "id", type = IdType.AUTO)
  @JsonFormat(shape = JsonFormat.Shape.STRING) // 使其返回类型为string
  private Integer id;

  @ApiModelProperty(value = "类型名称")
  @TableField("type_name")
  private String typeName;

  @ApiModelProperty(value = "图片地址，用于用户礼品兑换时显示")
  @TableField("img_url")
  private String imgUrl;

  @ApiModelProperty(value = "是否显示在礼品兑换,0不显示，1显示")
  @TableField("gift_show")
  private GiftTypeShowEnum giftShow;

  @ApiModelProperty(value = "信息创建时间")
  @TableField(value = "create_time", fill = FieldFill.INSERT)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 输出的格式
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
}
