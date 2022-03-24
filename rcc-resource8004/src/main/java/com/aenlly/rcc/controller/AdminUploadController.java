package com.aenlly.rcc.controller;

import com.aenlly.rcc.service.IUploadService;
import com.aenlly.rcc.utils.enums.UploadPathNameEnum;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author Aenlly
 * @create by date 2022/01/12 22:11
 * @projectName RefuseClassificationCultivate
 */
@RestController
@RequestMapping("/adminUpload")
public class AdminUploadController {

  @Resource private IUploadService uploadService;

  /**
   * 上传视频文件
   *
   * @param file 视频文件
   * @param pathNameEnum 保存服务器的相对地址
   * @return 远程存储相对地址
   */
  @PostMapping("/uploadVideo")
  public String uploadVideo(
      @RequestPart("videoFile") MultipartFile file,
      @ApiParam("保存的相对的地址") @RequestParam("path") UploadPathNameEnum pathNameEnum) {
    return uploadService.uploadVideo(file, pathNameEnum);
  }

  /**
   * 上传视频封面图片文件
   *
   * @param file 封面文件
   * @param pathNameEnum 保存服务器的相对地址
   * @return 远程存储相对地址
   */
  @PostMapping("/uploadImage")
  public String uploadImage(
      @RequestPart("imageFile") MultipartFile file,
      @ApiParam("保存的相对的地址") @RequestParam("path") UploadPathNameEnum pathNameEnum) {
    return uploadService.uploadImage(null, file, pathNameEnum);
  }
}
