package com.aenlly.rcc.service;

import com.aenlly.rcc.entity.WxUploadVideoInfo;
import com.aenlly.rcc.enums.UploadPathNameEnum;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Aenlly
 * @create by date 2021/12/19 22:09
 * @projectName RefuseClassificationCultivate
 */
public interface IUploadService {

  /**
   * 上传文件到指定目录目录
   *
   * @param userId 用户编号
   * @param files 文件
   * @param uploadPathNameEnum 上传文件夹名称枚举
   * @return 文件存储url
   */
  String UploadImage(String userId, MultipartFile files, UploadPathNameEnum uploadPathNameEnum);

  /**
   * 上传视频文件到临时目录
   *
   * @param file 文件
   * @param wxUploadVideoInfo 文件信息
   * @param uploadPathNameEnum 保存路径
   */
  String uploadTmpFile(
      byte[] file, WxUploadVideoInfo wxUploadVideoInfo, UploadPathNameEnum uploadPathNameEnum);

  /**
   * @param identifier 文件的 md5 值
   * @param fileName 文件名
   * @return 线上文件路径
   */
  String mergeTmpFile(String identifier, String fileName);
}
