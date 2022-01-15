package com.aenlly.rcc.admin.config;

import com.aenlly.rcc.config.IntegerNameToEnumConverterFactory;
import com.aenlly.rcc.config.StringToEnumConverterFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * springMVC配置
 *
 * @author Aenlly
 * @create by date 2021/12/19 14:38
 * @projectName RefuseClassificationCultivate
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  /** 枚举类的转换器工厂 addConverterFactory */
  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addConverterFactory(new IntegerNameToEnumConverterFactory());
    registry.addConverterFactory(new StringToEnumConverterFactory());
  }
}
