package com.aenlly.rcc.service;

import com.aenlly.rcc.enums.UploadPathNameEnum;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Aenlly
 * @create by date 2021/12/19 22:09
 * @projectName RefuseClassificationCultivate
 */
public interface IUploadService {

  /**
   * 上传文件到临时目录
   *
   * @param userId 用户编号
   * @param files 文件
   * @param uploadPathNameEnum 上传文件夹名称枚举
   * @return 文件存储url
   */
  String TempUploadImage(String userId, MultipartFile files, UploadPathNameEnum uploadPathNameEnum);
}
