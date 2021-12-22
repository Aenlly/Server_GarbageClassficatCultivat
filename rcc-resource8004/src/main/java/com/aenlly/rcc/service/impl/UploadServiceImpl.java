package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.entity.WxUploadVideoInfo;
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
   * 上传图片文件到指定目录
   *
   * @param userId 用户编号
   * @param files 文件
   * @param uploadPathNameEnum 上传文件夹名称枚举
   * @return 文件存储url
   */
  @Override
  public String UploadImage(
      String userId, MultipartFile files, UploadPathNameEnum uploadPathNameEnum) {
    // 当月文件夹
    String dateFile = uploadUtil.getDateFileName();
    // 上传文件附加路径
    String databasePath = String.format(uploadPathNameEnum.getName(), userId, dateFile);
    // 获得本地文件夹地址
    String localPath = uploadUtil.getLocalPath(databasePath);
    // 存储文件，并获得文件名
    String fileName = uploadUtil.getUploadFileName(files, localPath);
    // 存储至数据库中,存在则更新，并返回路径
    return uploadUtil.saveOrUpdateDatabase(databasePath, fileName);
  }
  /**
   * 上传视频文件到临时目录
   *
   * @param file 文件
   * @param wxUploadVideoInfo 文件信息
   * @param uploadPathNameEnum 保存路径
   */
  @Override
  public String uploadTmpFile(
      String file, WxUploadVideoInfo wxUploadVideoInfo, UploadPathNameEnum uploadPathNameEnum) {
    // 上传文件附加路径
    String databasePath =
        String.format(uploadPathNameEnum.getName(), wxUploadVideoInfo.getIdentifier());
    // 获得本地文件夹地址
    String localPath = uploadUtil.getLocalPath(databasePath);
    // 存储文件,返回的文件名不需要
    uploadUtil.uploadTmpVideoFileName(file, wxUploadVideoInfo, localPath);
    // 存储文件夹路径至数据库中并返回路径，而不是单独的文件路径存储
    return uploadUtil.saveOrUpdateDatabase(databasePath, null);
  }
}
