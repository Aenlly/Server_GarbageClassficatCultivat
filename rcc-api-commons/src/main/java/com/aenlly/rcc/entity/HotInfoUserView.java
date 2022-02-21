package com.aenlly.rcc.entity;

import com.aenlly.rcc.enums.HotInfoStateEnum;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
 * @since 2021-12-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties({"updateTime", "deleteFlag", "version"}) // json序列传递前端时忽略
@ApiModel(value = "用户服务热门资讯对象", description = "VIEW")
public class HotInfoUserView implements Serializable {

  private static final long serialVersionUID = -2687933707635927727L;

  @ApiModelProperty(value = "自增标识")
  @TableId(value = "hot_info_id", type = IdType.AUTO)
  @JsonFormat(shape = JsonFormat.Shape.STRING) // 使其返回类型为string
  private Integer hotInfoId;

  @ApiModelProperty(value = "资讯封面")
  @TableField("img_url")
  private String imgUrl;

  @ApiModelProperty(value = "资讯标题")
  @TableField("hot_info_title")
  private String hotInfoTitle;

  @ApiModelProperty(value = "资讯描述")
  @TableField("hot_info_desc")
  private String hotInfoDesc;

  @ApiModelProperty(value = "资讯内容")
  @TableField("hot_info_text")
  private String hotInfoText;

  @ApiModelProperty(value = "发布时间")
  @TableField("release_time")
  @JsonFormat(pattern = "yyyy-MM-dd") // 输出的格式
  private LocalDateTime releaseTime;

  @ApiModelProperty(value = "资讯状态，0:待发布、1:已发布、-1已下线")
  @TableField("hot_info_state")
  private HotInfoStateEnum hotInfoState;
}
