package com.aenlly.rcc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author aenlly
 * @since 2021-12-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "热门资讯实体对象", description = "")
public class HotInfo implements Serializable {

  private static final long serialVersionUID = -7106883385043369916L;

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

  @ApiModelProperty(value = "来源")
  @TableField("source_text")
  private String sourceText;

  @ApiModelProperty(value = "信息创建时间")
  @TableField("create_time")
  private LocalDateTime createTime;

  @ApiModelProperty(value = "信息更新时间")
  @TableField("update_time")
  private LocalDateTime updateTime;

  @ApiModelProperty(value = "逻辑删除,0未删除，1删除")
  @TableField("delete_flag")
  @TableLogic
  private Boolean deleteFlag;
}
