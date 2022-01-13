package com.aenlly.rcc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * VIEW
 *
 * @author aenlly
 * @since 2021-12-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "用户首页轮播显示对象", description = "VIEW")
public class CarouselUserView implements Serializable {

  private static final long serialVersionUID = 656188386967454576L;

  @ApiModelProperty(value = "标识字段")
  @TableId(value = "carousel_id", type = IdType.AUTO)
  @JsonFormat(shape = JsonFormat.Shape.STRING) // 使其返回类型为string
  private Integer carouselId;

  @ApiModelProperty(value = "轮播名称")
  private String name;

  @ApiModelProperty(value = "轮播图片")
  @TableField("img_url")
  private String imgUrl;

  @ApiModelProperty(value = "跳转地址")
  @TableField("activity_url")
  private String activityUrl;
}
