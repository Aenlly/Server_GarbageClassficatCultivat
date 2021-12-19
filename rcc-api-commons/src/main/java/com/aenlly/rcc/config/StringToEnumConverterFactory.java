package com.aenlly.rcc.config;

import com.aenlly.rcc.enums.BaseEnum;
import com.google.common.collect.Maps;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.Map;

/**
 * GET请求时，都是string，所以需要string转换工厂
 *
 * <p>map同时存储Name与Value，以便解析前端GET时传递整型
 *
 * <p>字符串 --》枚举 转换器工厂类
 *
 * <p>因为SpringBoot映射枚举时，默认用枚举名称映射
 *
 * <p>使用值映射需要进行转换 需要在springMVC中注册
 *
 * @author Aenlly
 * @create by date 2021/12/19 14:37
 * @projectName RefuseClassificationCultivate
 */
public class StringToEnumConverterFactory implements ConverterFactory<String, BaseEnum> {
  private static final Map<Class, Converter> CONVERTERS = Maps.newHashMap();

  /**
   * 获取一个从 Integer 转化为 T 的转换器，T 是一个泛型，有多个实现
   *
   * @param targetType 转换后的类型
   * @return 返回一个转化器
   */
  @Override
  public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
    Converter<String, T> converter = CONVERTERS.get(targetType);
    if (converter == null) {
      converter = new StringToEnumConverter<>(targetType);
      CONVERTERS.put(targetType, converter);
    }
    return converter;
  }
}
