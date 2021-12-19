package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.enums.UploadPathNameEnum;
import com.aenlly.rcc.service.IUploadService;
import com.aenlly.rcc.util.Setting;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author Aenlly
 * @create by date 2021/12/19 22:09
 * @projectName RefuseClassificationCultivate
 */
@Service
public class UploadServiceImpl implements IUploadService {

  @Resource private Setting setting;

  /**
   * 上传文件到临时目录
   *
   * @param userId 用户编号
   * @param files 文件
   * @param tempNameEnum 临时文件夹名称枚举
   * @return 文件存储url
   */
  @Override
  public String TempUploadImage(
      String userId, MultipartFile files, UploadPathNameEnum tempNameEnum) {
    String dateFileName = getDateFileName();
    String pathSplicing = String.format(tempNameEnum.getName(), userId, dateFileName);
    String uploadPath = getUploadPath(pathSplicing);
    return getUploadFilePath(files, uploadPath, pathSplicing);
  }

  /**
   * 获得上传的路径
   *
   * @param files 上传的文件
   * @param uploadPath 本地路径
   * @param databasePath 数据库存储路径文件夹
   * @return 数据库存储路径完整地址
   */
  private String getUploadFilePath(MultipartFile files, String uploadPath, String databasePath) {
    // 原始文件名
    String originalFilename = files.getOriginalFilename();
    // 新文件名
    String newFileName = UUID.randomUUID() + originalFilename;
    File file = new File(uploadPath, newFileName);
    try {
      // 保存到指定地址
      files.transferTo(file);
    } catch (IOException e) {
      return null;
    }
    System.out.println(file.getAbsolutePath());
    return databasePath + newFileName;
  }

  /**
   * 获得当前月为名称的文件夹名称
   *
   * @return 文件夹
   */
  public String getDateFileName() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
    return dateFormat.format(new Date());
  }

  /** 获取当前项目路径上传路径 */
  public String getUploadPath(String pathSplicing) {
    String path = setting.getINIT_PATH_NAME() + pathSplicing;
    File file = null;
    try {
      file = new File(path);
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (!file.exists()) {
      file.mkdirs();
    }
    return file.getAbsolutePath();
  }
}
