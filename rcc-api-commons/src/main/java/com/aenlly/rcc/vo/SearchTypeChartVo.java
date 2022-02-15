package com.aenlly.rcc.vo;

import com.aenlly.rcc.enums.SearchTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 搜索内容分组的图表数据
 *
 * @author Aenlly
 * @create by date 2022/02/15 15:55
 * @projectName RefuseClassificationCultivate
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SearchNameChartVo对象", description = "搜索类型分组的图表数据")
public class SearchTypeChartVo implements Serializable {

  private static final long serialVersionUID = -4983750429009000799L;

  @ApiModelProperty(value = "搜索类型，文本搜索，语音搜索，图片识别")
  @JsonProperty("name")
  private SearchTypeEnum type;

  @ApiModelProperty(value = "使用次数")
  private Long y;
}
