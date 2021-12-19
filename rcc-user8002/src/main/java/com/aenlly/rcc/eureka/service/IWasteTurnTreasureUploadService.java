package com.aenlly.rcc.eureka.service;

import com.aenlly.rcc.eureka.service.impl.WasteTurnTreasureUploadServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
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
}
