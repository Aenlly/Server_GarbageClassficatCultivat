package com.aenlly.rcc.utils.wrapper;

import com.aenlly.rcc.entity.AdminTable;
import com.aenlly.rcc.enums.AdminLoginEnum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 @author Aenlly
 @create by date 2022/01/08 20:08
 @projectName RefuseClassificationCultivate */
public class AdminWrapperUtil {

    /**
     邮箱
     */
    private static final String EMAIL_FILED = "email";
    /**
     手机号
     */
    private static final String TEL_FILED = "tel";

    /**
     利用账号与密码，创建查询对象

     @param username       账号
     @param adminLoginEnum 登录类型，邮箱或手机号
     @return 查询操作对象
     */
    public static QueryWrapper<AdminTable> adminLogin(String username, AdminLoginEnum adminLoginEnum) {
        QueryWrapper<AdminTable> wrapper = new QueryWrapper<>();
        if (adminLoginEnum.equals(AdminLoginEnum.EMAIL)) {
            wrapper.eq(EMAIL_FILED, username);
        } else if (adminLoginEnum.equals(AdminLoginEnum.TEL)) {
            wrapper.eq(TEL_FILED, username);
        }
        return wrapper;
    }
}
