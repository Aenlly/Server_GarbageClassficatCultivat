package com.aenlly.rcc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value = "用户服务热门资讯对象", description = "VIEW")
public class HotInfoUserView implements Serializable {

  private static final long serialVersionUID = -2687933707635927727L;

  @ApiModelProperty(value = "自增标识")
  @TableId(value = "hot_info_id", type = IdType.AUTO)
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
  private LocalDateTime releaseTime;

  @ApiModelProperty(value = "资讯状态，待发布、已发布、已下线")
  @TableField("hot_info_state")
  private String hotInfoState;

  @ApiModelProperty(value = "来源")
  @TableField("source_text")
  private String sourceText;
}
