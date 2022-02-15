package com.aenlly.rcc.utils.enums;

import com.aenlly.rcc.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据统计的时间条件
 *
 * @author Aenlly
 * @create by date 2022/02/15 16:13
 * @projectName RefuseClassificationCultivate
 */
@AllArgsConstructor
@Getter
public enum QueryTimeTypeEnum implements BaseEnum {
  /** 今天内的数据 */
  DAY(0, "to_days(create_time) = to_days(now())"),
  /** 近7天的数据 */
  SEVEN_DAYS(1, "DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(create_time)"),
  /** 近三十天的数据 */
  THIRTY_DAYS(2, "DATE_SUB(CURDATE(), INTERVAL 30 DAY) <= date(create_time)"),
  /** 上个月数据 */
  LAST_MONTH(
      3,
      "PERIOD_DIFF( " + "date_format( now( ) , '%Y%m' ) , date_format( create_time, '%Y%m' ) ) =1");

  private Integer name;
  private String value;
}
