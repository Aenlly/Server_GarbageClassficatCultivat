package com.aenlly.rcc.mapper;

import com.aenlly.rcc.entity.TmpFile;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 文件上传路径临时表，每天根据该表，删除未提交的信息到数据库的文件 Mapper 接口
 * </p>
 *
 * @author aenlly
 * @since 2021-12-21
 */
public interface TmpFileMapper extends BaseMapper<TmpFile> {

}
