package com.aenlly.rcc.eureka.service.impl;

import com.aenlly.rcc.eureka.service.IResourceUploadService;
import com.aenlly.rcc.utils.enums.UploadPathNameEnum;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Aenlly
 * @create by date 2022/01/12 22:16
 * @projectName RefuseClassificationCultivate
 */
public class ResourceUploadServiceImpl implements IResourceUploadService {
  @Override
  public String uploadVideo(MultipartFile file, UploadPathNameEnum pathNameEnum) {
    throw new NullPointerException();
  }

  @Override
  public String uploadImage(MultipartFile file, UploadPathNameEnum pathNameEnum) {
    throw new NullPointerException();
  }

  @Override
  public String uploadImage(String userId, MultipartFile file) {
    System.out.println("上传图片文件异常");
    throw new NullPointerException();
  }

  /**
   * 小程序上传的类型为MediaType.APPLICATION_OCTET_STREAM_VALUE
   *
   * <p>使用注解@RequestBody接收小程序分块上传的内容
   */
  @Override
  public String uploadTmpFile(
      byte[] bytes,
      String identifier,
      Long index,
      Long chunkSize,
      String fileName,
      Long totalChunks,
      Long totalSize,
      String userId) {
    System.out.println("上传视频文件异常");
    throw new NullPointerException();
  }

  /**
   * 调用合并分块请求接口
   *
   * @param identifier md5
   * @param fileName 文件名
   * @return 线上存储路径
   */
  @Override
  public String mergeTmpFile(String identifier, String fileName) {
    System.out.println("合并文件异常！");
    throw new NullPointerException();
  }
}
