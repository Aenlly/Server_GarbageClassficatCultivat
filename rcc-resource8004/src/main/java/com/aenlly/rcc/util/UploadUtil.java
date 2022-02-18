package com.aenlly.rcc.util;

import cn.hutool.core.collection.ListUtil;
import com.aenlly.rcc.entity.TmpFile;
import com.aenlly.rcc.entity.WxUploadVideoInfo;
import com.aenlly.rcc.service.ITmpFileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.util.ByteArrayBuffer;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Aenlly
 * @create by date 2021/12/20 22:13
 * @projectName RefuseClassificationCultivate
 */
@Component
@Slf4j
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
   * @param userId 用户编号,管理员上传时可不填写
   * @param databasePath 数据库存储路径
   * @param newFileName 文件名称
   * @return 数据库与文件名称的合并路径
   */
  public String saveOrUpdateDatabase(String userId, String databasePath, String newFileName) {
    if (newFileName != null) {
      databasePath = databasePath + "/" + newFileName;
    }
    // 保存附加的后半地址到数据库中，以便上传后的文件未进行使用，让其进行删除
    tmpFile.setUploadPath(databasePath);
    tmpFile.setUserId(userId);
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
   */
  public void uploadTmpVideoFileName(
      byte[] file, WxUploadVideoInfo wxUploadVideoInfo, String localPath) {
    // 获得文件唯一标识符
    String identifier = wxUploadVideoInfo.getIdentifier();
    // 获得文件序号
    Long index = wxUploadVideoInfo.getIndex();
    String fileName = identifier + "-" + index;
    // 保存到指定地址
    File files = new File(localPath, fileName);
    // io操作
    try (BufferedInputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(file));
        FileOutputStream fileOutputStream = new FileOutputStream(files); ) {
      ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer(5242880);
      int read = 0;
      // 写入到临时文件
      while ((read = inputStream.read(byteArrayBuffer.buffer())) != -1) {
        fileOutputStream.write(byteArrayBuffer.buffer(), 0, read);
      }
    } catch (IOException e) {
      throw new NullPointerException();
    }
  }

  /**
   * 合并文件方法，返回文件名
   *
   * @param identifier md5
   * @param localFolderPath 本地分块存储文件夹路径
   * @param databasePath 数据库路径
   * @param fileName 原始文件名
   * @return 文件名
   */
  public String mergeFile(
      String identifier, String localFolderPath, String databasePath, String fileName) {
    // 新文件名
    String newFileName = UUID.randomUUID() + fileName;
    // 分块保存路径
    String localPath = getLocalPath(localFolderPath);
    // 完整保存路径
    String savePath = getLocalPath(databasePath);
    // 获得分块保存路径文件夹
    File file = new File(localPath);
    // 保存文件
    File newFile = new File(savePath, newFileName);
    System.out.println(newFile.getAbsolutePath());
    // 文件数组
    File[] list = file.listFiles();
    // 转换成集合
    List<File> listFile = ListUtil.toList(list);
    // 升序排序
    listFile.sort(Comparator.comparing(a -> a.getAbsolutePath().split("-")[1]));
    // 合并文件
    listFile.forEach(
        (filePath) -> {
          // 利用try -catch-resources进行关闭流
          try (InputStream in = new FileInputStream(filePath);
              OutputStream out = new FileOutputStream(newFile, true);
              BufferedInputStream bufferedInputStream = new BufferedInputStream(in);
              BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(out); ) {
            // 缓冲区
            ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer(5242880);
            int read = 0;
            // 读取写入
            while ((read = bufferedInputStream.read(byteArrayBuffer.buffer())) != -1) {
              bufferedOutputStream.write(byteArrayBuffer.buffer(), 0, read);
            }
            filePath.delete();
          } catch (Exception e) {
            throw new NullPointerException();
          }
        });
    return newFileName;
  }

  /**
   * 获得上传者userId
   *
   * @param localFolderPath 上传的临时文件夹
   * @return 用户id
   */
  public String getUserId(String localFolderPath) {
    TmpFile tmpFile = tmpFileService.getById(localFolderPath);
    return tmpFile.getUserId();
  }
}
