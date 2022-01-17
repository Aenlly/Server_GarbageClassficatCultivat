package com.aenlly.rcc.admin.service.impl;

import com.aenlly.rcc.admin.service.IGiftInfoService;
import com.aenlly.rcc.entity.GiftInfo;
import com.aenlly.rcc.mapper.GiftInfoMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 单个礼品信息条目表 服务实现类
 *
 * @author aenlly
 * @since 2022-01-17
 */
@Service
public class GiftInfoServiceImpl extends ServiceImpl<GiftInfoMapper, GiftInfo>
    implements IGiftInfoService {}
