package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.entity.ItemPoolTable;
import com.aenlly.rcc.mapper.ItemPoolTableMapper;
import com.aenlly.rcc.service.IItemPoolTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 题库表 服务实现类
 *
 * @author aenlly
 * @since 2021-12-17
 */
@Service
public class ItemPoolTableServiceImpl extends ServiceImpl<ItemPoolTableMapper, ItemPoolTable>
    implements IItemPoolTableService {}
