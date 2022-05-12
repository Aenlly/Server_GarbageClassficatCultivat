package com.aenlly.rcc.utils.wrapper;

import com.aenlly.rcc.entity.HotInfo;
import com.aenlly.rcc.enums.HotInfoStateEnum;
import com.aenlly.rcc.utils.enums.QueryHotInfoTypeEnum;
import com.aenlly.rcc.vo.HotInfoVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;

import static com.aenlly.rcc.utils.enums.WrapperFiledConstant.*;

/**
 热门资讯信息表实体封装操作对象获取工具类

 @author Aenlly
 @create by date 2022/02/03 1:35
 @projectName RefuseClassificationCultivate */
public class HotInfoWrapperUtil {

    /**
     根据条件获取实体操作对象

     @param queryType 查询条件
     @param text      查询内容
     @param state     资讯状态条件，可为null
     @return 查询对象
     */
    public static Wrapper<HotInfoVo> queryListPage(QueryHotInfoTypeEnum queryType, String text, HotInfoStateEnum state) {
        QueryWrapper<HotInfoVo> wrapper = new QueryWrapper<>();
        wrapper.like(queryType.getValue(), text)
               .eq(DELETE_FLAG_FILED, DELETE_FLAG_VALUE)
               .orderByDesc(UPDATE_TIME_FILED);
        if (state != null) {
            wrapper.eq("hot_info_state", state);
        }
        return wrapper;
    }

    /**
     根据编号修改资讯状态，获取该操作对象

     @param id    编号
     @param state 资讯状态
     @return 更新对象
     */
    public static Wrapper<HotInfo> updateStateById(Long id, HotInfoStateEnum state) {
        UpdateWrapper<HotInfo> wrapper = new UpdateWrapper<>();
        wrapper.set("hot_info_state", state)
               .eq("hot_info_id", id)
               .orderByDesc(UPDATE_TIME_FILED);
        return wrapper;
    }
}
