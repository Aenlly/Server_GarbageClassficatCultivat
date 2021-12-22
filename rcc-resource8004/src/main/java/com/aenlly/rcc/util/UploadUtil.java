package com.aenlly.rcc.util;

import com.aenlly.rcc.entity.TmpFile;
import com.aenlly.rcc.entity.WxUploadVideoInfo;
import com.aenlly.rcc.service.ITmpFileService;
import org.apache.http.util.ByteArrayBuffer;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author Aenlly
 * @create by date 2021/12/20 22:13
 * @projectName RefuseClassificationCultivate
 */
@Component
public class UploadUtil {

  /** 配置数据类 */
  @Resource private Setting setting;
  /** 文件上传实体对象 */
  @Resource private TmpFile tmpFile;
  /** 文件上传表服务对象 */
  @Resource private ITmpFileService tmpFileService;

  /**
   * 进行文件存储
   *
   * @param files 上传的文件
   * @param localPath 本地路径
   * @return 存储成功的新文件名
   */
  public String getUploadFileName(MultipartFile files, String localPath) {
    // 原始文件名
    String originalFilename = files.getOriginalFilename();
    // 新文件名
    String newFileName = UUID.randomUUID() + originalFilename;
    File file = new File(localPath, newFileName);
    try {
      // 保存到指定地址
      files.transferTo(file);
    } catch (IOException e) {
      throw new NullPointerException();
    }
    // 返新文件名
    return newFileName;
  }

  /**
   * 存储信息到数据库中
   *
   * @param databasePath 数据库存储路径
   * @param newFileName 文件名称
   * @return 数据库与文件名称的合并路径
   */
  public String saveOrUpdateDatabase(String databasePath, String newFileName) {
    if (newFileName != null) {
      databasePath = databasePath + "/" + newFileName;
    }
    // 保存附加的后半地址到数据库中，以便上传后的文件未进行使用，让其进行删除
    tmpFile.setUploadPath(databasePath);
    boolean save = tmpFileService.saveOrUpdate(tmpFile);
    if (!save) {
      throw new NullPointerException();
    }
    return databasePath;
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

  /**
   * 获得本地文件夹地址
   *
   * @param pathSplicing 附加路径
   * @return 数据库路径
   */
  public String getLocalPath(String pathSplicing) {
    // 拼接本地存储路径
    String path = setting.getINIT_PATH_NAME() + pathSplicing;
    File file = new File(path);
    if (!file.exists()) {
      boolean mkdirs = file.mkdirs();
      if (!mkdirs) {
        throw new SecurityException();
      }
    }
    // 存储的本地地址
    return file.getAbsolutePath();
  }

  /**
   * 用户分块上传到暂存区
   *
   * @param file 文件二进制
   * @param wxUploadVideoInfo 文件信息
   * @param localPath 本地暂存区地址
   * @return 存储的文件名
   */
  public String uploadTmpVideoFileName(
      String file, WxUploadVideoInfo wxUploadVideoInfo, String localPath) {
    // 获得文件唯一标识符
    String identifier = wxUploadVideoInfo.getIdentifier();
    // 获得文件序号
    Long index = wxUploadVideoInfo.getIndex();
    String fileName = identifier + "-" + index;

    File files = new File(localPath, fileName);
    BufferedInputStream inputStream = null;
    FileOutputStream fileOutputStream = null;
    try {
      byte[] bytes = file.getBytes(StandardCharsets.UTF_8);
      // 保存到指定地址
      inputStream = new BufferedInputStream(new ByteArrayInputStream(bytes));
      fileOutputStream = new FileOutputStream(files);
      ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer(1024);
      int read = 0;
      while ((read = inputStream.read(byteArrayBuffer.buffer())) != -1) {
        fileOutputStream.write(byteArrayBuffer.buffer(), 0, read);
      }
    } catch (IOException e) {
      throw new NullPointerException();
    } finally {
      try {
        inputStream.close();
        fileOutputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return fileName;
  }
}
