package com.aenlly.rcc.entity;

import com.aenlly.rcc.enums.SubmitStateEnum;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 文件上传路径临时表，每天根据该表，删除未提交的信息到数据库的文件
 *
 * @author aenlly
 * @since 2021-12-21
 */
@Data
@Component
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "TmpFile对象", description = "文件上传路径临时表，每天根据该表，删除未提交的信息到数据库的文件")
public class TmpFile implements Serializable {

  private static final long serialVersionUID = 5541080810443692805L;

  @ApiModelProperty(value = "保存上传文件的路径")
  @TableId("upload_path")
  private String uploadPath;

  @ApiModelProperty(value = "上传的用户编号")
  @TableField("user_id")
  private String userId;

  @ApiModelProperty(value = "提交状态，0未提交，1已提交")
  private SubmitStateEnum state;

  @ApiModelProperty(value = "插入时间")
  @TableField("create_time")
  private LocalDateTime createTime;

  @ApiModelProperty(value = "信息更新时间")
  @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updateTime;
}
