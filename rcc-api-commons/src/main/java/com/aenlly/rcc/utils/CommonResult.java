package com.aenlly.rcc.utils;

import com.aenlly.rcc.enums.CodeMessageResultEnum;
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

  /**
   * 错误时使用
   *
   * @param messageResultEnum 返回类型
   */
  public CommonResult(CodeMessageResultEnum messageResultEnum) {
    this.code = messageResultEnum.getCode();
    this.message = messageResultEnum.getMessage();
  }

  /**
   * 正确时使用
   *
   * @param messageResultEnum 返回类型
   * @param data 返回内容
   */
  public CommonResult(CodeMessageResultEnum messageResultEnum, T data) {
    this.code = messageResultEnum.getCode();
    this.message = messageResultEnum.getMessage();
    this.data = data;
  }
}
