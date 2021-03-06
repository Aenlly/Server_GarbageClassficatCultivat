package com.aenlly.rcc.utils.wrapper;

import com.aenlly.rcc.entity.OrderUserView;
import com.aenlly.rcc.entity.PointsLog;
import com.aenlly.rcc.entity.User;
import com.aenlly.rcc.utils.enums.QueryPointsTypeEnum;
import com.aenlly.rcc.vo.UserVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.io.Serializable;

import static com.aenlly.rcc.utils.enums.WrapperFiledConstant.CREATE_TIME_FILED;
import static com.aenlly.rcc.utils.enums.WrapperFiledConstant.UPDATE_TIME_FILED;

/**
 用户信息的实体封装操作对象获取工具类

 @author Aenlly
 @create by date 2022/01/16 0:24
 @projectName RefuseClassificationCultivate */
public class UserWrapperUtil {

    /**
     根据查询条件获取操作对象

     @param text     查询内容
     @param typeEnum 排序条件，可省略
     @return 查询对象
     */
    public static Wrapper<UserVo> queryListPage(String text, QueryPointsTypeEnum typeEnum) {
        QueryWrapper<UserVo> wrapper = new QueryWrapper<>();
        wrapper.like("nick_name", text)
               .eq("user.delete_flag", 0);
        if (typeEnum != null) {
            wrapper.orderByDesc(typeEnum.getValue());
        }
        return wrapper;
    }

    /**
     根据用户id获得操作对象

     @param id 用户id
     @return 查询对象
     */
    public static Wrapper<User> getUserById(Serializable id) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("user_id", "nick_name", "avatar_url", "accumulative_points", "remaining_points", "answer_points", "points_id")
               .eq("user_id", id);
        return wrapper;
    }

    /**
     获得用户信息根据积分排序的操作对象

     @return 查询对象
     */
    public static Wrapper<User> getUserListByPoint() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("nick_name", "avatar_url", "accumulative_points")
               .orderByDesc("accumulative_points");
        return wrapper;
    }

    /**
     获得用户信息根据答题积分排序的操作对象

     @return 查询对象
     */
    public static Wrapper<User> getUserListByAnswerPoints() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("nick_name", "avatar_url", "answer_points")
               .orderByDesc("answer_points");
        return wrapper;
    }

    /**
     根据用户编号获取实体操作对象

     @param userId 用户编号
     @return 查询对象
     */
    public static Wrapper<OrderUserView> getOrderListById(String userId) {
        QueryWrapper<OrderUserView> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        return wrapper;
    }

    /**
     根据用户编号获取实体操作对象

     @param userId 用户编号
     @return 查询对象
     */
    public static Wrapper<PointsLog> getPointsListById(String userId) {
        QueryWrapper<PointsLog> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId)
               .orderByDesc(UPDATE_TIME_FILED);
        return wrapper;
    }

    /**
     根据用户编号 只获取昵称与头像操作对象

     @param id 用户编号
     @return 查询对象
     */
    public static Wrapper<User> getNameAndAvatarById(String id) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("nick_name", "avatar_url")
               .eq("user_id", id)
               .orderByDesc(CREATE_TIME_FILED);
        return wrapper;
    }
}
