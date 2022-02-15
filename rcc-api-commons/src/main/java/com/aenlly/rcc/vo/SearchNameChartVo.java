package com.aenlly.rcc.vo;

import com.baomidou.mybatisplus.annotation.TableField;
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
@ApiModel(value = "SearchNameChartVo对象", description = "搜索内容分组的图表数据")
public class SearchNameChartVo implements Serializable {
  private static final long serialVersionUID = -1145151515507079282L;

  @ApiModelProperty(value = "搜索的垃圾名称")
  @TableField("name")
  private String name;

  // @JsonFormat(shape = JsonFormat.Shape.STRING) // 使其返回类型为string
  @ApiModelProperty(value = "搜索次数")
  private Long y;
}
