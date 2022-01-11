package com.aenlly.rcc.enums;

/**
 * 数据库插入值的枚举顶级接口 所有枚举映射都需要基础该接口
 *
 * <p>需要转换的枚举类都必须继承该接口
 *
 * @author Aenlly
 * @create by date 2021/12/19 14:33
 * @projectName RefuseClassificationCultivate
 */
public interface BaseEnum {
  /** 数据库字段值 */
  Integer getName();

  /** 前端映射值 */
  String getValue();
}
