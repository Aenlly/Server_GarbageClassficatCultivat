package com.aenlly.rcc.utils.wrapper;

import com.aenlly.rcc.entity.Carousel;
import com.aenlly.rcc.enums.CarouselShowFlagEnum;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;

import static com.aenlly.rcc.utils.enums.WrapperFiledConstant.NAME_FILED;
import static com.aenlly.rcc.utils.enums.WrapperFiledConstant.UPDATE_TIME_FILED;

/**
 轮播信息的实体封装操作对象获取工具类

 @author Aenlly
 @create by date 2022/01/14 16:58
 @projectName RefuseClassificationCultivate */
public class CarouselWrapperUtil {
    /**
     是否显示
     */
    private static final String SHOW_FLAG_FILED = "show_flag";

    /**
     carousel_id编号
     */
    private static final String CAROUSEL_ID_FILED = "carousel_id";

    /**
     根据查询条件获取操作对象

     @param text 查询内容
     @return 查询对象
     */
    public static Wrapper<Carousel> queryListPage(String text) {
        QueryWrapper<Carousel> wrapper = new QueryWrapper<>();
        wrapper.like(NAME_FILED, text)
               .orderByDesc(SHOW_FLAG_FILED)
               .orderByDesc(UPDATE_TIME_FILED);
        return wrapper;
    }

    /**
     根据上线状态获取操作对象

     @param online 上线状态
     @return 查询对象
     */
    public static Wrapper<Carousel> getOnlineCount(CarouselShowFlagEnum online) {
        QueryWrapper<Carousel> wrapper = new QueryWrapper<>();
        wrapper.select(NAME_FILED)
               .eq(SHOW_FLAG_FILED, online)
               .orderBy(true, false, UPDATE_TIME_FILED);
        return wrapper;
    }

    /**
     根据id与上线状态获取操作对象

     @param id       编号
     @param flagEnum 状态
     @return 更新对象
     */
    public static Wrapper<Carousel> updateByIdPutState(Long id, CarouselShowFlagEnum flagEnum) {
        UpdateWrapper<Carousel> wrapper = new UpdateWrapper<>();
        wrapper.set(SHOW_FLAG_FILED, flagEnum)
               .eq(CAROUSEL_ID_FILED, id)
               .orderBy(true, false, UPDATE_TIME_FILED);
        return wrapper;
    }
}
