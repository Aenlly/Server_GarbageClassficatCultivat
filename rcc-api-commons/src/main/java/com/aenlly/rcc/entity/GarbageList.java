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
@ApiModel(value = "垃圾类型所属详情对象", description = "")
public class GarbageList implements Serializable {

  private static final long serialVersionUID = -2015484827969168645L;

  @ApiModelProperty(value = "标识字段")
  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  @ApiModelProperty(value = "垃圾所属类型id")
  @TableField("garbage_id")
  private Integer garbageId;

  @ApiModelProperty(value = "垃圾名称")
  @TableField("garbage_name")
  private String garbageName;

  @ApiModelProperty(value = "垃圾图片地址")
  @TableField("img_url")
  private String imgUrl;

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
}
