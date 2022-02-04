package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.entity.ItemPoolSubjectView;
import com.aenlly.rcc.mapper.ItemPoolSubjectViewMapper;
import com.aenlly.rcc.service.IItemPoolSubjectViewService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 题库表与题目表的多表 VIEW 服务实现类
 *
 * @author aenlly
 * @since 2022-02-04
 */
@Service
public class ItemPoolSubjectViewServiceImpl
    extends ServiceImpl<ItemPoolSubjectViewMapper, ItemPoolSubjectView>
    implements IItemPoolSubjectViewService {}
