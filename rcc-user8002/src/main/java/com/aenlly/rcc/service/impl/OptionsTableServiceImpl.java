package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.entity.OptionsTable;
import com.aenlly.rcc.mapper.OptionsTableMapper;
import com.aenlly.rcc.service.IOptionsTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 选项表 服务实现类
 *
 * @author aenlly
 * @since 2021-12-17
 */
@Service
public class OptionsTableServiceImpl extends ServiceImpl<OptionsTableMapper, OptionsTable>
    implements IOptionsTableService {}
