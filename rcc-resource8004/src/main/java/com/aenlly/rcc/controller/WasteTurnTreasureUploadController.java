package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.WxUploadVideoInfo;
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
    return uploadService.UploadImage(userId, files, UploadPathNameEnum.WASTE_IMAGE_NAME);
  }

  /**
   * 小程序分块发送的content-type是application/octet-stream类型，即MediaType.APPLICATION_OCTET_STREAM_VALUE
   *
   * <p>使用@RequestBody接收二进制文件
   *
   * <p>同时使用注解@RequestPart接收，而不是@RequestParam
   */
  @PostMapping(value = "/uploadTmpFile", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
  public String uploadTmpFile(@RequestBody String file, WxUploadVideoInfo wxUploadVideoInfo) {
    return uploadService.uploadTmpFile(
        file, wxUploadVideoInfo, UploadPathNameEnum.WASTE_TEMP_FILE_NAME);
  }

  /**
   * 合并分块请求
   *
   * @param identifier md5值
   * @param fileName 文件名
   * @return 线上数据库文件路径
   */
  @GetMapping("/mergeTmpFile")
  public String mergeTmpFile(
      @RequestParam("identifier") String identifier, @RequestParam("fileName") String fileName) {

    return null;
  }
}
