package com.aenlly.rcc.utils.wrapper;

import com.aenlly.rcc.entity.GarbageList;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import static com.aenlly.rcc.utils.enums.WrapperFiledConstant.UPDATE_TIME_FILED;

/**
 所属垃圾类型条目信息表的实体封装操作对象获取工具类

 @author Aenlly
 @create by date 2022/01/15 17:51
 @projectName RefuseClassificationCultivate */
public class GarbageListWrapperUtil {
    /**
     编号
     */
    private static final String GARBAGE_ID_FILED = "garbage_id";
    /**
     name
     */
    private static final String NAME_FILED = "garbage_name";

    /**
     根据条件获取实体封装操作对象

     @param belongId 所属垃圾类型id
     @param text     查询内容
     @return 查询对象
     */
    public static Wrapper<GarbageList> queryListPage(Integer belongId, String text) {
        QueryWrapper<GarbageList> wrapper = new QueryWrapper<>();
        wrapper.eq(GARBAGE_ID_FILED, belongId)
               .like(NAME_FILED, text)
               .orderByDesc(UPDATE_TIME_FILED);
        return wrapper;
    }
}
