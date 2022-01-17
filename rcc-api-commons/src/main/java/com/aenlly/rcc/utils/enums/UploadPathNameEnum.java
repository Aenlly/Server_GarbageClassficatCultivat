package com.aenlly.rcc.utils.enums;

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

  /** 视频信息的视频文件地址，占位的地方填写年份-月份 */
  VIDEO_IMAGE_NAME("/video/image/%s"),
  /** 视频信息的封面文件地址，占位的地方填写年份-月份 */
  VIDEO_VIDEO_NAME("/video/video/%s"),
  /** 视频信息的封面文件地址，占位的地方填写年份-月份 */
  CAROUSEL_IMAGE_NAME("/carousel/video/%s"),
  /** 垃圾类型视频存储地址 */
  GARBAGE_VIDEO_NAME("/garbage/video"),
  /** 垃圾类型条目图标存储地址，占位符的地方填写年份-月份 */
  GARBAGE_IMAGE_NAME("/garbage/image/%s"),
  /** 垃圾类型图标存储地址 */
  GARBAGE_ICO_NAME("/garbage/ico"),
  /** 礼品封面的存储地址，占位的地址填写年份-月份 */
  GIFT_IMAGE_NAME("/garbage/image/%s"),
  /** 变废为宝的封面文件地址，占位的地方分别填写用户名，年份-月份 */
  WASTE_IMAGE_NAME("/waste/image/%s/%s"),
  /** 临时存储地址,占位地方填写文件唯一标识符 */
  WASTE_TEMP_FILE_NAME("/waste/tmp/%s"),
  /** 占位的地方分别填写用户名，年份-月份，文件名 */
  WASTE_FILE_NAME("/waste/video/%s/%s");

  private String name;
}
