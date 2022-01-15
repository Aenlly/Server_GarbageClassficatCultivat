package com.aenlly.rcc.user.service.impl;

import com.aenlly.rcc.entity.SubjectTable;
import com.aenlly.rcc.mapper.SubjectTableMapper;
import com.aenlly.rcc.user.service.ISubjectTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 题目表 服务实现类
 *
 * @author aenlly
 * @since 2021-12-17
 */
@Service
public class SubjectTableServiceImpl extends ServiceImpl<SubjectTableMapper, SubjectTable>
    implements ISubjectTableService {}
