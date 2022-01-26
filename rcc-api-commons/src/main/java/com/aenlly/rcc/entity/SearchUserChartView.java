package com.aenlly.rcc.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 搜索记录图表数据 VIEW
 *
 * @author aenlly
 * @since 2022-01-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SearchUserChartView对象", description = "VIEW,搜索记录图表数据，近30天")
public class SearchUserChartView implements Serializable {

  private static final long serialVersionUID = -5999589388331605303L;

  @ApiModelProperty(value = "搜索的垃圾名称")
  private String name;

  private Long sum;

  @ApiModelProperty(value = "搜索类型，文本搜索，语音搜索，图片识别")
  private Integer type;
}
