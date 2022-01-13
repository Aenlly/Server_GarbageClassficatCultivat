package com.aenlly.rcc.controller;

import com.aenlly.rcc.enums.UploadPathNameEnum;
import com.aenlly.rcc.service.IUploadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author Aenlly
 * @create by date 2022/01/12 22:11
 * @projectName RefuseClassificationCultivate
 */
@RestController
@RequestMapping("/videoUpload")
public class VideoUploadController {

  @Resource private IUploadService uploadService;

  /**
   * 上传视频文件
   *
   * @param file 视频文件
   * @return 远程存储相对地址
   */
  @PostMapping("/uploadVideo")
  public String uploadVideo(@RequestPart("videoFile") MultipartFile file) {
    return uploadService.uploadVideo(file, UploadPathNameEnum.VIDEO_IMAGE_NAME);
  }

  /**
   * 上传视频封面图片文件
   *
   * @param file 封面文件
   * @return 远程存储相对地址
   */
  @PostMapping("/uploadImage")
  public String uploadImage(@RequestPart("imageFile") MultipartFile file) {
    return uploadService.uploadImage(null, file, UploadPathNameEnum.VIDEO_FILE_NAME);
  }
}
