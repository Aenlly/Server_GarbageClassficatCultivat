package com.aenlly.rcc.config;

import cn.hutool.core.util.ObjectUtil;
import com.aenlly.rcc.enums.BaseEnum;
import com.google.common.collect.Maps;
import org.springframework.core.convert.converter.Converter;

import java.util.Map;

/**
 * GET请求时，都是string，所以需要string转换工厂
 *
 * <p>map同时存储Name与Value，以便解析前端GET时传递整型
 *
 * <p>字符串 --》枚举 转换器 因为SpringBoot映射枚举时，默认用枚举名称映射 使用值映射需要进行转换
 *
 * @author Aenlly
 * @create by date 2021/12/19 14:37
 * @projectName RefuseClassificationCultivate
 */
public class StringToEnumConverter<T extends BaseEnum> implements Converter<String, T> {
  private Map<String, T> enumMap = Maps.newHashMap();

  public StringToEnumConverter(Class<T> enumType) {
    T[] enums = enumType.getEnumConstants();
    for (T e : enums) {
      enumMap.put(e.getName().toString(), e);
      enumMap.put(e.getValue(), e);
    }
  }

  @Override
  public T convert(String value) {
    T t = enumMap.get(value);
    if (ObjectUtil.isNull(t)) {
      throw new IllegalArgumentException("无法匹配对应的枚举类型");
    }
    return t;
  }
}
