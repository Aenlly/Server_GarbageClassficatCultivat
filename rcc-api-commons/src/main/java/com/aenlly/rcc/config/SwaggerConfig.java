package com.aenlly.rcc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Aenlly
 * @create by date 2021/11/07 14:49
 * @projectName RefuseClassificationCultivate
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

  /***
   *创建API
   */
  @Bean
  public Docket createRestApi(Environment environment) {
    // 设置要显示环境，在配置文件中配置
    Profiles profiles = Profiles.of("dev");
    // 通过environment.acceptsProfiles(profiles)判断是否处在自己设定的环境
    boolean flag = environment.acceptsProfiles(profiles);
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .host("")
        // 分组，多个组则创建多个Docket实例
        .groupName("Aenlly")
        // 是否启动swagger
        .enable(flag)
        .select()
        // 配置要扫描接口的方式
        // basePackage(): 扫描指定的包
        // any():扫描全部，none()：不扫描
        // withClassAnnotation(): 扫描类上的注解
        // withMethodAnnotation(): 扫描方法上的注解
        .apis(RequestHandlerSelectors.basePackage("com.aenlly.rcc.controller"))
        // 过滤路径
        // .paths(PathSelectors.any())
        .build();
  }

  /***
   * Api的基本配置
   * title标题
   * description描述
   * version版本号
   * @return api基本信息
   */
  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("垃圾分类养成的接口文档")
        .termsOfServiceUrl("")
        .contact(
            new Contact(
                "Aenlly",
                "https://blog.csdn.net/qq_42186271?spm=1010.2135.3001.5421",
                "2645274834@qq.com"))
        .description("垃圾分类养成相关接口的文档")
        .version("1.0")
        .license("")
        .licenseUrl("")
        .build();
  }
}
