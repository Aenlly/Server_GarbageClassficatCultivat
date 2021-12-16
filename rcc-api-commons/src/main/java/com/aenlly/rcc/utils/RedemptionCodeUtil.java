package com.aenlly.rcc.utils;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 兑换码生成根据类,只能使用静态方法
 *
 * @author Aenlly
 * @create by date 2021/12/15 19:48
 * @projectName RefuseClassificationCultivate
 */
public class RedemptionCodeUtil {

  private RedemptionCodeUtil() {}

  private static final String[] chars =
      new String[] {
        "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T",
        "U", "V", "W", "X", "Y", "Z", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D",
        "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
        "Y", "Z", "2", "3", "4", "5", "6", "7", "8", "9"
      };

  /**
   * 生成兑换码
   *
   * @return 兑换码
   */
  public static String generateShortUuid() {
    StringBuilder shortBuffer = new StringBuilder();
    String uuid =
        UUID.randomUUID().toString().replace("-", "")
            + UUID.randomUUID().toString().replace("-", "");
    for (int i = 0; i < 12; i++) {
      String str = uuid.substring(i * 4, i * 4 + 4);
      int x = Integer.parseInt(str, 16);
      shortBuffer.append(chars[x % 0x3E]);
    }
    String str =
        shortBuffer.substring(0, 4)
            + "-"
            + shortBuffer.substring(4, 8)
            + "-"
            + shortBuffer.substring(8, 12);
    Pattern pattern = Pattern.compile("[1-9]");
    Matcher matcher = pattern.matcher(str);
    // 必须包含数字
    if (!matcher.find()) {
      str = generateShortUuid();
    }
    return str;
  }
}
