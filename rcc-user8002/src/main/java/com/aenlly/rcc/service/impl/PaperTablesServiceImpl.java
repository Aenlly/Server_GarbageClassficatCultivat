package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.entity.PaperTables;
import com.aenlly.rcc.mapper.PaperTablesMapper;
import com.aenlly.rcc.service.IPaperTablesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 答卷表 服务实现类
 *
 * @author aenlly
 * @since 2021-12-17
 */
@Service
public class PaperTablesServiceImpl extends ServiceImpl<PaperTablesMapper, PaperTables>
    implements IPaperTablesService {}
