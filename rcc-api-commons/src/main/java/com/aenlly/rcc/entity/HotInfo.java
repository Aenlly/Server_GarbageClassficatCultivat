package com.aenlly.rcc.entity;

import com.aenlly.rcc.enums.HotInfoStateEnum;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 热门资讯实体类
 *
 * @author aenlly
 * @since 2022-02-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties({"hotInfoState", "createTime", "deleteFlag", "version"}) // json序列传递前端时忽略
@ApiModel(value = "HotInfo对象", description = "热门资讯表")
public class HotInfo implements Serializable {

  private static final long serialVersionUID = -4210439474306394295L;

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
  @JsonFormat(pattern = "yyyy-MM-dd") // 输出的格式
  @DateTimeFormat(pattern = "yyyy-MM-dd") // 接收日期类型转换，yyyy-MM-dd使用类型LocalDate
  private LocalDate releaseTime; // yyyy-MM-dd HH:mm:ss类型使用LocalDateTime

  @ApiModelProperty(value = "来源")
  @TableField("source_text")
  private String sourceText;

  @ApiModelProperty(value = "资讯状态，0:待发布、1:已发布、-1已下线")
  @TableField("hot_info_state")
  private HotInfoStateEnum hotInfoState;

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
