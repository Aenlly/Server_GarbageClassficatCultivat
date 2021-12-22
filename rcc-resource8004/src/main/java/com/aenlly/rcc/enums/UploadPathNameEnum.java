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
  /** 占位的地方分别填写用户名，年份-月份 */
  WASTE_IMAGE_NAME("/waste/image/%s/%s"),
  /** 临时存储地址,占位地方填写文件唯一标识符 */
  WASTE_TEMP_FILE_NAME("/waste/tmp/%s"),
  /** 占位的地方分别填写用户名，年份-月份，文件名 */
  WASTE_FILE_NAME("/waste/video/%s/%s%s");

  private String name;
}
