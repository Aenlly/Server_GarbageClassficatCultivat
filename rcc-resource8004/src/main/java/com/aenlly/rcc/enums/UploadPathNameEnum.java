package com.aenlly.rcc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Aenlly
 * @create by date 2021/12/19 22:10
 * @projectName RefuseClassificationCultivate
 */
@Getter
@AllArgsConstructor
public enum UploadPathNameEnum {
  WASTE_IMAGE_NAME("/waste/image/%s/%s");

  private String name;
}
