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
 * @since 2021-12-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "用户搜索记录对象", description = "")
public class UserSearch implements Serializable {

  private static final long serialVersionUID = 8406083679967308845L;

  @TableId(value = "search_id", type = IdType.AUTO)
  private Integer searchId;

  @ApiModelProperty(value = "搜索的垃圾名称")
  private String name;

  @ApiModelProperty(value = "搜索类型，文本搜索，语音搜索，图片识别")
  private String type;

  @TableField("user_id")
  private Integer userId;

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

  @ApiModelProperty(value = "版本号，用于乐观锁")
  @Version
  private Integer version;

  public UserSearch(String name, String userId) {}
}
