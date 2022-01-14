package com.aenlly.rcc.eureka.service.impl;

import com.aenlly.rcc.eureka.service.IAdminUploadService;
import com.aenlly.rcc.utils.enums.UploadPathNameEnum;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Aenlly
 * @create by date 2022/01/12 22:16
 * @projectName RefuseClassificationCultivate
 */
public class AdminUploadServiceImpl implements IAdminUploadService {
  @Override
  public String uploadVideo(MultipartFile file, UploadPathNameEnum pathNameEnum) {
    throw new NullPointerException();
  }

  @Override
  public String uploadImage(MultipartFile file, UploadPathNameEnum pathNameEnum) {
    throw new NullPointerException();
  }
}
