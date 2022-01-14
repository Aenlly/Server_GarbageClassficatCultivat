package com.aenlly.rcc.eureka.service;

import com.aenlly.rcc.eureka.service.impl.AdminUploadServiceImpl;
import com.aenlly.rcc.utils.enums.UploadPathNameEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Aenlly
 * @create by date 2022/01/12 22:16
 * @projectName RefuseClassificationCultivate
 */
@Component
@FeignClient(value = "resource", fallback = AdminUploadServiceImpl.class) // 服务名称
public interface IAdminUploadService {

  @PostMapping(value = "/adminUpload/uploadVideo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  String uploadVideo(
      @RequestPart("videoFile") MultipartFile file,
      @RequestParam("path") UploadPathNameEnum pathNameEnum);

  @PostMapping(value = "/adminUpload/uploadImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  String uploadImage(
      @RequestPart("imageFile") MultipartFile file,
      @RequestParam("path") UploadPathNameEnum pathNameEnum);
}
