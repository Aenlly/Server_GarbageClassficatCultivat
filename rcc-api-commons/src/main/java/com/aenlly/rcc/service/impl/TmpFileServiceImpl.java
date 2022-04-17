package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.entity.TmpFile;
import com.aenlly.rcc.enums.SubmitStateEnum;
import com.aenlly.rcc.mapper.TmpFileMapper;
import com.aenlly.rcc.service.ITmpFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 文件上传路径临时表，每天根据该表，删除未提交的信息到数据库的文件 服务实现类
 *
 * @author aenlly
 * @since 2021-12-21
 */
@Service
public class TmpFileServiceImpl extends ServiceImpl<TmpFileMapper, TmpFile>
    implements ITmpFileService {

  /**
   * 更新临时存储的视频与图片数据状态为已提交
   *
   * @param fileUrl 文件地址
   * @return 是否成功
   */
  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {UnsupportedOperationException.class, NullPointerException.class, RuntimeException.class})
  public Boolean updateBatchTmpInfo(String... fileUrl) {
    Collection<TmpFile> tmpFiles = new ArrayList<>();
    for (String path : fileUrl) {
      // 创建需要修改的文件信息
      TmpFile tmpFile = new TmpFile();
      tmpFile.setUploadPath(path);
      tmpFile.setState(SubmitStateEnum.SUBMITTED);
      // 添加至集合中
      tmpFiles.add(tmpFile);
    }
    return this.updateBatchById(tmpFiles);
  }
}
