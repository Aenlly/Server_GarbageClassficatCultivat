package com.aenlly.rcc.entity;

import com.aenlly.rcc.enums.SearchTypeEnum;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 首页用户搜索库表
 *
 * @author aenlly
 * @since 2022-01-26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties({
  "searchId",
  "userId",
  "createTime",
  "updateTime",
  "deleteFlag",
  "version"
}) // json序列传递前端时忽略
@ApiModel(value = "用户搜索记录对象", description = "首页用户搜索库表")
public class SearchUser implements Serializable {

  private static final long serialVersionUID = 8406083679967308845L;

  @ApiModelProperty(value = "自增标识")
  @TableId(value = "search_id", type = IdType.AUTO)
  @JsonFormat(shape = JsonFormat.Shape.STRING) // 使其返回类型为string
  private Integer searchId;

  @ApiModelProperty(value = "搜索的垃圾名称")
  @TableField("name")
  private String name;

  @ApiModelProperty(value = "搜索类型，文本搜索，语音搜索，图片识别")
  @TableField("type")
  private SearchTypeEnum type;

  @ApiModelProperty(value = "用户编号")
  @TableField("user_id")
  private String userId;

  @ApiModelProperty(value = "信息创建时间")
  @TableField(value = "create_time", fill = FieldFill.INSERT)
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

  public SearchUser(String name, SearchTypeEnum type, String userId) {
    this.name = name;
    this.type = type;
    this.userId = userId;
  }
}
