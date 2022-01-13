package com.aenlly.rcc.entity;

import com.aenlly.rcc.enums.WasteTagEnum;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author aenlly
 * @since 2021-12-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "收藏Entity对象", description = "")
public class CollectEntity implements Serializable {

  private static final long serialVersionUID = 2921471695918752300L;

  @ApiModelProperty(value = "自增标识")
  @TableId(value = "collect_id", type = IdType.AUTO)
  @JsonFormat(shape = JsonFormat.Shape.STRING) // 使其返回类型为string
  private Integer collectId;

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

  @ApiModelProperty(value = "标签，好用，好看，好玩，收藏变废为宝时使用")
  @TableField("text_tag")
  private WasteTagEnum textTag;

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
