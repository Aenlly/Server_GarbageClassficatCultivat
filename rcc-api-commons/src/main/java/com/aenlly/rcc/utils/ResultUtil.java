package com.aenlly.rcc.utils;

/**
 * 返回公共内容信息创建类
 *
 * <p>使用静态方法
 *
 * @author Aenlly
 * @create by date 2021/12/16 15:27
 * @projectName RefuseClassificationCultivate
 */
public class ResultUtil {

  private ResultUtil() {}

  /**
   * 请求数据操作成功
   *
   * <p>统一返回内容执行方法
   *
   * @param t 返回内容
   * @param <T> 类型
   * @return 公共返回内容
   */
  public static <T> CommonResult<T> resultOk(T t) {
    return new CommonResult<T>(CodeMessageResultEnum.OK, t);
  }

  /**
   * 请求数据操作失败
   *
   * <p>统一返回内容方法执行方法
   *
   * @param <T> 推断类型，不需要
   * @return 公共错误返回内容
   */
  public static <T> CommonResult<T> resultError() {
    return new CommonResult<T>(CodeMessageResultEnum.ERROR);
  }
}
