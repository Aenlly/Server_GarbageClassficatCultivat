package com.aenlly.rcc.eureka.service;

import com.aenlly.rcc.eureka.service.impl.WasteTurnTreasureUploadServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Aenlly
 * @create by date 2021/12/19 18:13
 * @projectName RefuseClassificationCultivate
 */
@Component
@FeignClient(value = "resource", fallback = WasteTurnTreasureUploadServiceImpl.class) // 服务名称
public interface IWasteTurnTreasureUploadService {

  @PostMapping(
      value = "/WasteTurnTreasureUpload/UploadImage",
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  String UploadImage(
      @RequestParam("userId") String userId, @RequestPart("files") MultipartFile files);

  /** 小程序上传的类型为MediaType.APPLICATION_OCTET_STREAM_VALUE 使用注解@RequestBody接收小程序分块上传的内容 */
  @PostMapping(
      value = "/WasteTurnTreasureUpload/uploadTmpFile",
      consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
  String uploadTmpFile(
      @RequestBody String file,
      @RequestParam("identifier") String identifier,
      @RequestParam("index") Long index,
      @RequestParam("chunkSize") Long chunkSize,
      @RequestParam("fileName") String fileName,
      @RequestParam("totalChunks") Long totalChunks,
      @RequestParam("totalSize") Long totalSize,
      @RequestParam("userId") String userId);
}
