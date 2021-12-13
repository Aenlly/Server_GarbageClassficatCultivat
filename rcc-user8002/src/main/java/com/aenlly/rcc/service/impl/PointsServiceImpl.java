package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.entity.Points;
import com.aenlly.rcc.mapper.PointsMapper;
import com.aenlly.rcc.service.IPointsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author aenlly
 * @since 2021-12-11
 */
@Service
public class PointsServiceImpl extends ServiceImpl<PointsMapper, Points>
    implements IPointsService {}