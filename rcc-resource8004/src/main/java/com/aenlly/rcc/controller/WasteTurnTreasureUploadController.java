package com.aenlly.rcc.controller;

import com.aenlly.rcc.enums.UploadPathNameEnum;
import com.aenlly.rcc.service.IUploadService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 服务调用，需要修改host文件，否则发现不到
 *
 * @author Aenlly
 * @create by date 2021/12/19 18:08
 * @projectName RefuseClassificationCultivate
 */
@RestController
@RequestMapping("/WasteTurnTreasureUpload")
public class WasteTurnTreasureUploadController {

  @Resource private IUploadService uploadService;

  /**
   * 文件接收需要添加，, consumes = MediaType.MULTIPART_FORM_DATA_VALUE
   *
   * <p>同时使用注解@RequestPart接收，而不是@RequestParam
   */
  @PostMapping(value = "/UploadImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public String UploadImage(
      @RequestParam("userId") String userId, @RequestPart("files") MultipartFile files) {
    return uploadService.TempUploadImage(userId, files, UploadPathNameEnum.WASTE_IMAGE_NAME);
  }
}
