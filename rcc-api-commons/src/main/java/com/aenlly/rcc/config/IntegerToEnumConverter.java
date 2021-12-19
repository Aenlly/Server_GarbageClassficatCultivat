package com.aenlly.rcc.config;

import cn.hutool.core.util.ObjectUtil;
import com.aenlly.rcc.enums.BaseEnum;
import com.google.common.collect.Maps;
import org.springframework.core.convert.converter.Converter;

import java.util.Map;

/**
 * 整型 --》枚举 转换器 因为SpringBoot映射枚举时，默认用枚举名称映射 使用值映射需要进行转换
 *
 * @author Aenlly
 * @create by date 2021/12/19 14:32
 * @projectName RefuseClassificationCultivate
 */
public class IntegerToEnumConverter<T extends BaseEnum> implements Converter<Integer, T> {
  private Map<Integer, T> enumMap = Maps.newHashMap();

  public IntegerToEnumConverter(Class<T> enumType) {
    T[] enums = enumType.getEnumConstants();
    for (T e : enums) {
      enumMap.put(e.getName(), e);
    }
  }

  @Override
  public T convert(Integer name) {
    T t = enumMap.get(name);
    if (ObjectUtil.isNull(t)) {
      throw new IllegalArgumentException("无法匹配对应的枚举类型");
    }
    return t;
  }
}
