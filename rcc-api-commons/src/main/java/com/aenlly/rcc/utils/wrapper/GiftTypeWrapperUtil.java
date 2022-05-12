package com.aenlly.rcc.utils.wrapper;

import com.aenlly.rcc.entity.GiftType;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import static com.aenlly.rcc.utils.enums.WrapperFiledConstant.*;

/**
 礼品类型实体表获取数据操作封装工具类

 @author Aenlly
 @create by date 2022/01/17 20:10
 @projectName RefuseClassificationCultivate */
public class GiftTypeWrapperUtil {

    /**
     根据类型名称获取操作对象

     @param text 类型名称
     @return 查询对象
     */
    public static Wrapper<GiftType> queryByTypeName(String text) {
        QueryWrapper<GiftType> wrapper = new QueryWrapper<>();
        wrapper.select(ID_FILED, "type_name")
               .like("type_name", text)
               .orderByDesc(UPDATE_TIME_FILED);
        return wrapper;
    }

    /**
     根据查询内容，获取操作对象

     @param text 查询内容
     */
    public static Wrapper<GiftType> queryListPage(String text) {
        QueryWrapper<GiftType> wrapper = new QueryWrapper<>();
        wrapper.like("type_name", text)
               .orderByDesc(UPDATE_TIME_FILED);
        return wrapper;
    }

    /**
     获得用户积分礼品显示的类型内容操作对象

     @return 查询对象
     */
    public static Wrapper<GiftType> getUserGiftTypeList() {
        QueryWrapper<GiftType> wrapper = new QueryWrapper<>();
        wrapper.select(ID_FILED, "type_name", IMG_URL_FILED)
               .eq("gift_show", 1)
               .orderByDesc(UPDATE_TIME_FILED)
               .last("limit 4");
        return wrapper;
    }
}
