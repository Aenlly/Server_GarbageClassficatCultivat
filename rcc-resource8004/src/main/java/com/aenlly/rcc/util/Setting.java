package com.aenlly.rcc.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Aenlly
 * @create by date 2021/12/19 23:23
 * @projectName RefuseClassificationCultivate
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Setting {

  @Value("${upload.path}")
  private String INIT_PATH_NAME;
}
