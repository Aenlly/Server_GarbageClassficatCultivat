package com.aenlly.rcc.vo;

import com.aenlly.rcc.enums.HotInfoStateEnum;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author Aenlly
 * @create by date 2022/02/03 1:41
 * @projectName RefuseClassificationCultivate
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties({"deleteFlag"}) // json序列传递前端时忽略
@ApiModel(value = "变废为宝信息对象", description = "变废为宝与收藏/点赞多表")
public class HotInfoVo {

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

  @ApiModelProperty(value = "资讯状态，0:待发布、1:已发布、-1已下线")
  @TableField("hot_info_state")
  private HotInfoStateEnum hotInfoState;

  @ApiModelProperty(value = "点赞总数")
  @TableField("like_count")
  @JsonFormat(shape = JsonFormat.Shape.STRING) // 使其返回类型为string,避免精度丢失
  private Long likeCount;

  @ApiModelProperty(value = "收藏总数")
  @TableField("collect_count")
  @JsonFormat(shape = JsonFormat.Shape.STRING) // 使其返回类型为string,避免精度丢失
  private Long collectCount;

  @ApiModelProperty(value = "信息创建时间")
  @TableField(value = "create_time", fill = FieldFill.INSERT)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 输出的格式
  private LocalDateTime createTime;
}
