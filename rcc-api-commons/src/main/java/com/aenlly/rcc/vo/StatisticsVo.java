package com.aenlly.rcc.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Aenlly
 * @create by date 2022/02/15 16:41
 * @projectName RefuseClassificationCultivate
 */
@Data
@Component
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "StatisticsVo对象", description = "统计数据返回实体信息")
public class StatisticsVo {

  @JsonFormat(shape = JsonFormat.Shape.STRING) // 使其返回类型为string
  public Long userCount;

  @JsonFormat(shape = JsonFormat.Shape.STRING) // 使其返回类型为string
  public Long signCount;

  @JsonFormat(shape = JsonFormat.Shape.STRING) // 使其返回类型为string
  public Long collectCount;

  @JsonFormat(shape = JsonFormat.Shape.STRING) // 使其返回类型为string
  public Long likeCount;

  public List<Map<String, SearchNameChartVo>> columnChart;

  public List<Map<String, SearchTypeChartVo>> pieChart;
}
