package com.aenlly.rcc.service.impl;

import com.aenlly.rcc.entity.WxUploadVideoInfo;
import com.aenlly.rcc.service.IUploadService;
import com.aenlly.rcc.util.UploadUtil;
import com.aenlly.rcc.utils.enums.UploadPathNameEnum;
import org.apache.commons.lang3.StringUtils;
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
   * @param userId 用户编号,管理员上传时可不填
   * @param files 文件
   * @param uploadPathNameEnum 上传文件夹名称枚举
   * @return 文件存储url
   */
  @Override
  public String uploadImage(
      String userId, MultipartFile files, UploadPathNameEnum uploadPathNameEnum) {
    return uploadFile(userId, files, uploadPathNameEnum);
  }

  /**
   * 上传文件到指定目录
   *
   * @param userId 用户编号,管理员上传时可不填
   * @param files 文件
   * @param uploadPathNameEnum 上传文件夹名称枚举
   * @return 文件存储url
   */
  private String uploadFile(
      String userId, MultipartFile files, UploadPathNameEnum uploadPathNameEnum) {
    // 当月文件夹
    String dateFile = uploadUtil.getDateFileName();
    // 上传文件附加路径
    String databasePath;
    if (StringUtils.isNotBlank(userId)) {
      databasePath = String.format(uploadPathNameEnum.getName(), userId, dateFile);
    } else {
      databasePath = String.format(uploadPathNameEnum.getName(), dateFile);
    }
    // 获得本地文件夹地址
    String localPath = uploadUtil.getLocalPath(databasePath);
    // 存储文件，并获得文件名
    String fileName = uploadUtil.getUploadFileName(files, localPath);
    // 存储至数据库中,存在则更新，并返回路径
    return uploadUtil.saveOrUpdateDatabase(userId, databasePath, fileName);
  }
  /**
   * 上传视频文件到临时目录
   *
   * @param file 文件字节数组
   * @param wxUploadVideoInfo 文件信息
   * @param uploadPathNameEnum 保存路径
   */
  @Override
  public String uploadTmpFile(
      byte[] file, WxUploadVideoInfo wxUploadVideoInfo, UploadPathNameEnum uploadPathNameEnum) {
    // 上传文件附加路径
    String databasePath =
        String.format(uploadPathNameEnum.getName(), wxUploadVideoInfo.getIdentifier());
    // 获得本地文件夹地址
    String localPath = uploadUtil.getLocalPath(databasePath);
    // 存储文件,返回的文件名不需要
    uploadUtil.uploadTmpVideoFileName(file, wxUploadVideoInfo, localPath);
    // 存储文件夹路径至数据库中并返回路径，而不是单独的文件路径存储
    return uploadUtil.saveOrUpdateDatabase(wxUploadVideoInfo.getUserId(), databasePath, null);
  }

  /**
   * @param identifier 文件的 md5 值
   * @param fileName 文件名
   * @return 线上文件路径
   */
  @Override
  public String mergeTmpFile(String identifier, String fileName) {
    // 当月文件夹
    String dateFile = uploadUtil.getDateFileName();
    // 临时路径文件夹
    String localFolderPath =
        String.format(UploadPathNameEnum.WASTE_TEMP_FILE_NAME.getName(), identifier);

    String userId = uploadUtil.getUserId(localFolderPath);

    // 保存附加路径
    String databasePath =
        String.format(UploadPathNameEnum.WASTE_FILE_NAME.getName(), userId, dateFile);

    // 合并文件
    fileName = uploadUtil.mergeFile(identifier, localFolderPath, databasePath, fileName);
    return uploadUtil.saveOrUpdateDatabase(userId, databasePath, fileName);
  }

  /**
   * 上传视频文件
   *
   * @param file 视频文件
   * @param videoFileName 视频存储文件夹位置
   * @return 视频存储相对远程地址
   */
  @Override
  public String uploadVideo(MultipartFile file, UploadPathNameEnum videoFileName) {
    return uploadFile(null, file, videoFileName);
  }
}
