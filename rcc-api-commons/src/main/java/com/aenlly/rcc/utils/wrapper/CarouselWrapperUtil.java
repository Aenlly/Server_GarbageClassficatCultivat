package com.aenlly.rcc.utils.wrapper;

import com.aenlly.rcc.entity.Carousel;
import com.aenlly.rcc.enums.CarouselShowFlagEnum;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;

/**
 * 轮播图的数据库操作对象获取工具类
 *
 * @author Aenlly
 * @create by date 2022/01/14 16:58
 * @projectName RefuseClassificationCultivate
 */
public class CarouselWrapperUtil {

  /**
   * 根据查询条件获取操作对象
   *
   * @param text 查询内容
   * @return 查询对象
   */
  public static Wrapper<Carousel> queryListPage(String text) {
    QueryWrapper<Carousel> wrapper = new QueryWrapper<>();
    wrapper.like("name", text).orderByDesc("show_flag").orderBy(true, false, "update_time");
    return wrapper;
  }

  /**
   * 根据上线状态获取操作对象
   *
   * @param online 上线状态
   * @return 查询对象
   */
  public static Wrapper<Carousel> getOnlineCount(CarouselShowFlagEnum online) {
    QueryWrapper<Carousel> wrapper = new QueryWrapper<>();
    wrapper.select("name").eq("show_flag", online);
    return wrapper;
  }

  /**
   * 根据id与上线状态获取操作对象
   *
   * @param id 编号
   * @param flagEnum 状态
   * @return 更新对象
   */
  public static Wrapper<Carousel> updateByIdPutState(Long id, CarouselShowFlagEnum flagEnum) {
    UpdateWrapper<Carousel> wrapper = new UpdateWrapper<>();
    wrapper.set("show_flag", flagEnum).eq("carousel_id", id);
    return wrapper;
  }
}
