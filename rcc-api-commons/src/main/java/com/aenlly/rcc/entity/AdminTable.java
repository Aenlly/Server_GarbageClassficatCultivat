package com.aenlly.rcc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 管理员表
 *
 * @author aenlly
 * @since 2022-01-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AdminTable对象", description = "管理员表")
public class AdminTable implements Serializable {

  private static final long serialVersionUID = 3303024819989789508L;

  @ApiModelProperty(value = "编号")
  private Long id;

  @ApiModelProperty(value = "头像，需要上传到其他地方使用")
  @TableField("img_url")
  private String imgUrl;

  @ApiModelProperty(value = "管理者名称")
  private String name;

  @ApiModelProperty(value = "邮箱")
  private String email;

  @ApiModelProperty(value = "手机号")
  private String tel;

  @ApiModelProperty(value = "密码，加密")
  private String password;
}
