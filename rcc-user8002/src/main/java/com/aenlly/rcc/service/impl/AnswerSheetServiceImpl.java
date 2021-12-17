package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.entity.AnswerSheet;
import com.aenlly.rcc.mapper.AnswerSheetMapper;
import com.aenlly.rcc.service.IAnswerSheetService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 答卷-答案表 服务实现类
 *
 * @author aenlly
 * @since 2021-12-17
 */
@Service
public class AnswerSheetServiceImpl extends ServiceImpl<AnswerSheetMapper, AnswerSheet>
    implements IAnswerSheetService {}
