package com.aenlly.rcc.eureka.service;

import com.aenlly.rcc.eureka.service.impl.ResourceUploadServiceImpl;
import com.aenlly.rcc.utils.enums.UploadPathNameEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Aenlly
 * @create by date 2022/01/12 22:16
 * @projectName RefuseClassificationCultivate
 */
@Component
@FeignClient(value = "resource", fallback = ResourceUploadServiceImpl.class) // 服务名称
public interface IResourceUploadService {

  @PostMapping(value = "/adminUpload/uploadVideo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  String uploadVideo(
      @RequestPart("videoFile") MultipartFile file,
      @RequestParam("path") UploadPathNameEnum pathNameEnum);

  @PostMapping(value = "/adminUpload/uploadImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  String uploadImage(
      @RequestPart("imageFile") MultipartFile file,
      @RequestParam("path") UploadPathNameEnum pathNameEnum);

  @PostMapping(value = "/userUpload/uploadImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  String uploadImage(
      @RequestParam("userId") String userId, @RequestPart("files") MultipartFile files);

  /**
   * 小程序上传的类型为MediaType.APPLICATION_OCTET_STREAM_VALUE
   *
   * <p>使用注解@RequestBody 发送小程序分块上传的内容
   */
  @PostMapping(
      value = "/userUpload/uploadTmpFile",
      consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
  String uploadTmpFile(
      @RequestBody byte[] bytes,
      @RequestParam("identifier") String identifier,
      @RequestParam("index") Long index,
      @RequestParam("chunkSize") Long chunkSize,
      @RequestParam("fileName") String fileName,
      @RequestParam("totalChunks") Long totalChunks,
      @RequestParam("totalSize") Long totalSize,
      @RequestParam("userId") String userId);

  /**
   * 调用合并分块请求接口
   *
   * @param identifier md5
   * @param fileName 文件名
   * @return 线上存储路径
   */
  @GetMapping(value = "/userUpload/mergeTmpFile")
  String mergeTmpFile(
      @RequestParam("identifier") String identifier, @RequestParam("fileName") String fileName);
}
