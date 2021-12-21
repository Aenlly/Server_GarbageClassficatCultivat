package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.enums.UploadPathNameEnum;
import com.aenlly.rcc.service.IUploadService;
import com.aenlly.rcc.util.UploadUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author Aenlly
 * @create by date 2021/12/19 22:09
 * @projectName RefuseClassificationCultivate
 */
@Service
public class UploadServiceImpl implements IUploadService {

  @Resource private UploadUtil uploadUtil;

  /**
   * 上传文件到临时目录
   *
   * @param userId 用户编号
   * @param files 文件
   * @param uploadPathNameEnum 上传文件夹名称枚举
   * @return 文件存储url
   */
  @Override
  public String TempUploadImage(
      String userId, MultipartFile files, UploadPathNameEnum uploadPathNameEnum) {
    // 上传文件名
    String dateFileName = uploadUtil.getDateFileName();
    // 上传文件附加路径
    String pathSplicing = String.format(uploadPathNameEnum.getName(), userId, dateFileName);
    return uploadUtil.getUploadPath(files, pathSplicing);
  }
}
