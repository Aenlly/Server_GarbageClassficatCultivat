package com.aenlly.rcc.entity;

import com.aenlly.rcc.enums.AuditEnum;
import com.aenlly.rcc.enums.UserUploadEnum;
import com.aenlly.rcc.enums.WasteTagEnum;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 变废为宝表
 *
 * @author aenlly
 * @since 2021-12-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "WasteTurnTreasure对象", description = "变废为宝表")
public class WasteTurnTreasure implements Serializable {

  private static final long serialVersionUID = 7774823841079931305L;

  @ApiModelProperty(value = "自增标识")
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  @ApiModelProperty(value = "标题")
  private String text;

  @ApiModelProperty(value = "描述")
  @TableField("text_desc")
  private String textDesc;

  @ApiModelProperty(value = "标签，0好用，1好看，2好玩，")
  @TableField("text_tag")
  private WasteTagEnum textTag;

  @ApiModelProperty(value = "视频地址")
  @TableField("video_url")
  private String videoUrl;

  @ApiModelProperty(value = "封面")
  @TableField("img_url")
  private String imgUrl;

  @ApiModelProperty(value = "信息审核，-1未通过，0待审核，1已通过，3已下架")
  private AuditEnum audit;

  @ApiModelProperty(value = "是用户上传，0不是，1是")
  @TableField("is_user_upload")
  private UserUploadEnum isUserUpload;

  @TableField("admin_id")
  private Integer adminId;

  @TableField("user_id")
  private String userId;

  @ApiModelProperty(value = "分享总数")
  @TableField("share_count")
  private Long shareCount;

  @ApiModelProperty(value = "插入时间")
  @TableField("create_time")
  private LocalDateTime createTime;

  @ApiModelProperty(value = "更新时间")
  @TableField("update_time")
  private LocalDateTime updateTime;

  @ApiModelProperty(value = "逻辑删除,0未删除，1删除")
  @TableField("delete_flag")
  @TableLogic
  private Boolean deleteFlag;

  @ApiModelProperty(value = "版本号，用于乐观锁")
  @Version
  private Integer version;

  @ApiModelProperty("发布者信息")
  @TableField(exist = false) // 非数据库字段
  private User user;
}
