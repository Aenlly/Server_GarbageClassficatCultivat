package com.aenlly.rcc.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Aenlly
 * @create by date 2021/11/07 14:13
 * @projectName RefuseClassificationCultivate
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "公共返回类", description = "公共返回类")
public class CommonResult<T> implements Serializable {
  private static final long serialVersionUID = 8178772928508874210L;
  /** 状态码* */
  @ApiModelProperty(example = "返回状态")
  private Integer code;
  /** 提示内容* */
  @ApiModelProperty(example = "返回信息")
  private String message;
  /** 返回内容* */
  @ApiModelProperty(example = "返回内容")
  private T data;

  private CommonResult(CodeResult code, MessageResult message) {
    this.code = code.getCode();
    this.message = message.getMsg();
  }

  public CommonResult(CodeResult code, MessageResult message, T data) {
    this.code = code.getCode();
    this.message = message.getMsg();
    this.data = data;
  }
}
