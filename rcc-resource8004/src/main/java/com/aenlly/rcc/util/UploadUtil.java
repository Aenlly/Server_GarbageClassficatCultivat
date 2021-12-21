package com.aenlly.rcc.util;

import com.aenlly.rcc.entity.TmpFile;
import com.aenlly.rcc.service.ITmpFileService;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
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
  // /** 文件上传实体对象 */
  @Resource private TmpFile tmpFile;
  /** 文件上传表服务对象 */
  @Resource private ITmpFileService tmpFileService;

  /**
   * 进行文件存储
   *
   * @param files 上传的文件
   * @param uploadPath 本地路径
   * @param databasePath 数据库存储路径文件夹
   * @return 数据库存储路径
   */
  private String getUploadFilePath(MultipartFile files, String uploadPath, String databasePath) {
    // 原始文件名
    String originalFilename = files.getOriginalFilename();
    // 新文件名
    String newFileName = UUID.randomUUID() + originalFilename;
    File file = new File(uploadPath, newFileName);
    databasePath = databasePath + "/" + newFileName;
    try {
      // 保存到指定地址
      files.transferTo(file);
      // 保存附加的后半地址到数据库中，以便未上传使用的文件进行删除
      tmpFile.setUploadPath(databasePath);
      tmpFileService.save(tmpFile);
    } catch (IOException e) {
      throw new NullPointerException();
    }
    // 返数据库存储路径
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
   * 获得文件存储的数据库路径
   *
   * @param files 文件
   * @param pathSplicing 附加路径
   * @return 数据库路径
   */
  public String getUploadPath(MultipartFile files, String pathSplicing) {
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
    String absolutePath = file.getAbsolutePath();
    // 存储文件,并返回数据库存储路径
    return getUploadFilePath(files, absolutePath, pathSplicing);
  }
}
