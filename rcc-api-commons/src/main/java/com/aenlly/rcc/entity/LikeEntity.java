package com.aenlly.rcc.entity;

import com.baomidou.mybatisplus.annotation.*;
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
@ApiModel(value = "点赞Entity对象", description = "点赞不需要逻辑删除")
public class LikeEntity implements Serializable {

  private static final long serialVersionUID = -6088954969296202525L;

  @ApiModelProperty(value = "自增标识")
  @TableId(value = "like_id", type = IdType.AUTO)
  private Integer likeId;

  @ApiModelProperty(value = "用户唯一标识")
  @TableField("user_id")
  private String userId;

  @ApiModelProperty(value = "收藏实体名称")
  @TableField("entity_name")
  private String entityName;

  @ApiModelProperty(value = "收藏数据id")
  @TableField("data_id")
  private String dataId;

  @ApiModelProperty(value = "收藏数据图片封面")
  @TableField("img_url")
  private String imgUrl;

  @ApiModelProperty(value = "收藏数据标题")
  @TableField("data_title")
  private String dataTitle;

  @ApiModelProperty(value = "收藏数据描述")
  @TableField("data_desc")
  private String dataDesc;

  @ApiModelProperty(value = "信息创建时间")
  @TableField(value = "create_time", fill = FieldFill.INSERT)
  private LocalDateTime createTime;

  @ApiModelProperty(value = "信息更新时间")
  @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updateTime;

  @ApiModelProperty(value = "版本号，用于乐观锁")
  @Version
  private Integer version;
}
