package com.aenlly.rcc.vo;

import com.aenlly.rcc.enums.AuditEnum;
import com.aenlly.rcc.enums.IsUserUploadEnum;
import com.aenlly.rcc.enums.WasteTagEnum;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Aenlly
 * @create by date 2022/01/31 15:34
 * @projectName RefuseClassificationCultivate
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "变废为宝信息对象", description = "变废为宝与收藏/点赞多表")
public class WasteTurnTreasureVo implements Serializable {

  private static final long serialVersionUID = 8450078958935227993L;

  @ApiModelProperty(value = "自增标识")
  @TableId(value = "id", type = IdType.AUTO)
  @JsonFormat(shape = JsonFormat.Shape.STRING) // 使其返回类型为string,避免精度丢失
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

  @ApiModelProperty(value = "信息审核，-1未通过，0待审核，1已发布，3已下架")
  private AuditEnum audit;

  @ApiModelProperty(value = "是用户上传，0不是，1是")
  @TableField("is_user_upload")
  private IsUserUploadEnum isUserUpload;

  @ApiModelProperty(value = "上传者编号")
  @TableField("promulgator_id")
  private String promulgatorId;

  @ApiModelProperty(value = "点赞总数")
  @TableField("like_count")
  @JsonFormat(shape = JsonFormat.Shape.STRING) // 使其返回类型为string,避免精度丢失
  private Long likeCount;

  @ApiModelProperty(value = "收藏总数")
  @TableField("collect_count")
  @JsonFormat(shape = JsonFormat.Shape.STRING) // 使其返回类型为string,避免精度丢失
  private Long collectCount;

  @ApiModelProperty(value = "分享总数")
  @TableField("share_count")
  @JsonFormat(shape = JsonFormat.Shape.STRING) // 使其返回类型为string,避免精度丢失
  private Long shareCount;

  @ApiModelProperty(value = "插入时间")
  @TableField("create_time")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 输出的格式
  private LocalDateTime createTime;

  @ApiModelProperty(value = "更新时间")
  @TableField("update_time")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 输出的格式
  private LocalDateTime updateTime;
}
