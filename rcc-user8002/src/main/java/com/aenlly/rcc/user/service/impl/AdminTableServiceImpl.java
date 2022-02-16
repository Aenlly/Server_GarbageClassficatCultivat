package com.aenlly.rcc.user.service.impl;

import com.aenlly.rcc.entity.AdminTable;
import com.aenlly.rcc.mapper.AdminTableMapper;
import com.aenlly.rcc.user.service.IAdminTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 管理员表 服务实现类
 *
 * @author aenlly
 * @since 2022-01-08
 */
@Service
public class AdminTableServiceImpl extends ServiceImpl<AdminTableMapper, AdminTable>
    implements IAdminTableService {}
