package com.aenlly.rcc.service;

import com.aenlly.rcc.entity.TmpFile;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 文件上传路径临时表，每天根据该表，删除未提交的信息到数据库的文件 服务类
 *
 * @author aenlly
 * @since 2021-12-21
 */
public interface ITmpFileService extends IService<TmpFile> {

  /**
   * 更新临时存储的视频与图片数据状态为已提交
   *
   * @param fileUrl 文件地址
   * @return 是否成功
   */
  Boolean updateBatchTmpInfo(String... fileUrl);
}
