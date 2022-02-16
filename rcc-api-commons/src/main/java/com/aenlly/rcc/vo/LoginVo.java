package com.aenlly.rcc.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author Aenlly
 * @create by date 2022/02/16 17:44
 * @projectName RefuseClassificationCultivate
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "登录信息数据返回", description = "登录信息数据返回")
public class LoginVo implements Serializable {

  private static final long serialVersionUID = 4864448912031541402L;

  @ApiModelProperty(value = "token")
  private String token;

  @ApiModelProperty(value = "昵称")
  private String name;

  @ApiModelProperty(value = "头像地址")
  private String imgUrl;
}
