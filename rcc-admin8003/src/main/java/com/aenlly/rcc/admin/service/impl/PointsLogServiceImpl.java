package com.aenlly.rcc.admin.service.impl;

import com.aenlly.rcc.admin.service.IPointsLogService;
import com.aenlly.rcc.entity.PointsLog;
import com.aenlly.rcc.mapper.PointsLogMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author aenlly
 * @since 2022-01-16
 */
@Service
public class PointsLogServiceImpl extends ServiceImpl<PointsLogMapper, PointsLog>
    implements IPointsLogService {}
