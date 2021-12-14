package com.aenlly.rcc.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Aenlly
 * @create by date 2021/12/14 21:51
 * @projectName RefuseClassificationCultivate
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum SearchType {
  SEARCH_TEXT("文字搜索"),
  SEARCH_VOICE("语音搜索"),
  SEARCH_PICTURE("图片识别");

  private String value;
}
