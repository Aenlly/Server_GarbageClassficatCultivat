package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.entity.TmpFile;
import com.aenlly.rcc.enums.SubmitStateEnum;
import com.aenlly.rcc.mapper.TmpFileMapper;
import com.aenlly.rcc.service.ITmpFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
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
   * @param videoUrl 视频地址
   * @param videoImage 图片地址
   * @return 是否成功
   */
  @Override
  @Transactional
  public Boolean updateBatchTmpInfo(String videoUrl, String videoImage) {
    Collection<TmpFile> tmpFiles = new ArrayList<>();
    // 创建需要修改的视图片频信息
    TmpFile tmpFileVideo = new TmpFile();
    tmpFileVideo.setUploadPath(videoUrl);
    tmpFileVideo.setState(SubmitStateEnum.SUBMITTED);
    // 添加至集合中
    tmpFiles.add(tmpFileVideo);
    // 创建需要修改的视频信息
    TmpFile tmpFileImage = new TmpFile();
    tmpFileImage.setUploadPath(videoImage);
    tmpFileImage.setState(SubmitStateEnum.SUBMITTED);
    // 添加至集合中
    tmpFiles.add(tmpFileImage);
    return this.updateBatchById(tmpFiles);
  }
}
