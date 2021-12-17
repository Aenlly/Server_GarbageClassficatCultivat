package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.entity.QuestionnaireTopics;
import com.aenlly.rcc.mapper.QuestionnaireTopicsMapper;
import com.aenlly.rcc.service.IQuestionnaireTopicsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 问卷题目对应表 服务实现类
 *
 * @author aenlly
 * @since 2021-12-17
 */
@Service
public class QuestionnaireTopicsServiceImpl
    extends ServiceImpl<QuestionnaireTopicsMapper, QuestionnaireTopics>
    implements IQuestionnaireTopicsService {}
