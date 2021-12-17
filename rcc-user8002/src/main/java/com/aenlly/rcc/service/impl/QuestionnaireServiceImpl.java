package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.entity.Questionnaire;
import com.aenlly.rcc.mapper.QuestionnaireMapper;
import com.aenlly.rcc.service.IQuestionnaireService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 问卷表 服务实现类
 *
 * @author aenlly
 * @since 2021-12-17
 */
@Service
public class QuestionnaireServiceImpl extends ServiceImpl<QuestionnaireMapper, Questionnaire>
    implements IQuestionnaireService {}
