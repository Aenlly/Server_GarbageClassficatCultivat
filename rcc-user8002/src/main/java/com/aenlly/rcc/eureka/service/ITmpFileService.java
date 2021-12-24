package com.aenlly.rcc.eureka.service;

import com.aenlly.rcc.entity.TmpFile;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 文件上传路径临时表，每天根据该表，删除未提交的信息到数据库的文件 服务类
 *
 * @author aenlly
 * @since 2021-12-21
 */
public interface ITmpFileService extends IService<TmpFile> {}
