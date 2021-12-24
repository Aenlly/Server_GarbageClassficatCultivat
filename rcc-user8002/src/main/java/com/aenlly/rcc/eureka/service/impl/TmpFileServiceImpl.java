package com.aenlly.rcc.eureka.service.impl;

import com.aenlly.rcc.entity.TmpFile;
import com.aenlly.rcc.eureka.service.ITmpFileService;
import com.aenlly.rcc.mapper.TmpFileMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 文件上传路径临时表，每天根据该表，删除未提交的信息到数据库的文件 服务实现类
 *
 * @author aenlly
 * @since 2021-12-21
 */
@Service
public class TmpFileServiceImpl extends ServiceImpl<TmpFileMapper, TmpFile>
    implements ITmpFileService {}
