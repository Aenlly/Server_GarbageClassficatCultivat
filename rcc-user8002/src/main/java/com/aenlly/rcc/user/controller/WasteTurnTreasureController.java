package com.aenlly.rcc.user.controller;

import com.aenlly.rcc.entity.AdminTable;
import com.aenlly.rcc.entity.User;
import com.aenlly.rcc.entity.WasteTurnTreasure;
import com.aenlly.rcc.entity.WxUploadVideoInfo;
import com.aenlly.rcc.enums.AuditEnum;
import com.aenlly.rcc.enums.IsUserUploadEnum;
import com.aenlly.rcc.enums.WasteTagEnum;
import com.aenlly.rcc.eureka.service.IResourceUploadService;
import com.aenlly.rcc.user.service.IAdminTableService;
import com.aenlly.rcc.user.service.IUserService;
import com.aenlly.rcc.user.service.IWasteTurnTreasureService;
import com.aenlly.rcc.user.utils.TokenUtil;
import com.aenlly.rcc.utils.CommonResult;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;

import static com.aenlly.rcc.utils.ResultUtil.resultError;
import static com.aenlly.rcc.utils.ResultUtil.resultOk;

/**
 * 变废为宝表 前端控制器
 *
 * @author aenlly
 * @since 2021-12-18
 */
@RestController
@Slf4j
@Api(tags = "用户服务-变废为宝管理控制器")
@RequestMapping("/waste-turn-treasure")
public class WasteTurnTreasureController {

  /** 变废为宝表-服务对象 */
  @Resource private IWasteTurnTreasureService wasteTurnTreasureService;
  /** 变废为宝上传服务调用接口 */
  @Resource private IResourceUploadService uploadService;

  @Resource private IUserService userService;

  @Resource private IAdminTableService adminTableService;

  @ApiOperation(value = "用户服务-变废为宝-初始请求-根据标签搜索，获取信息请求", httpMethod = "GET")
  @GetMapping("/getListByTag/{tag}")
  @JsonCreator
  public CommonResult<List<WasteTurnTreasure>> getListByTag(
      @Param("标签") @PathVariable("tag") WasteTagEnum wasteTagEnum) {
    try {
      List<WasteTurnTreasure> list = wasteTurnTreasureService.queryListByTag(wasteTagEnum);
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "用户服务-变废为宝-根据标题搜索，获取信息请求", httpMethod = "GET")
  @GetMapping("/getListSearchByTitle/{title}")
  public CommonResult<List<WasteTurnTreasure>> getListSearchByTitle(
      @Param("标题") @PathVariable("title") String title) {
    try {
      List<WasteTurnTreasure> list = wasteTurnTreasureService.queryListSearchByTitle(title);
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "用户服务-变废为宝-根据id信息详情请求")
  @GetMapping("/getOneById/{id}")
  public CommonResult<WasteTurnTreasure> getOneById(@Param("变废为宝编号") @PathVariable("id") Long id) {
    try {
      WasteTurnTreasure wasteTurnTreasure = wasteTurnTreasureService.getById(id);
      User user = new User();
      if (wasteTurnTreasure.getIsUserUpload().equals(IsUserUploadEnum.YES)) {
        user = userService.getNameAndAvatarById(wasteTurnTreasure.getPromulgatorId());
      } else {
        AdminTable adminTable = adminTableService.getById(wasteTurnTreasure.getPromulgatorId());
        user.setAvatarUrl(adminTable.getImgUrl());
        user.setNickName(adminTable.getName());
      }
      wasteTurnTreasure.setUser(user);
      return resultOk(wasteTurnTreasure);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "分享操作，用户通过分享的链接进入时，分享数量增加", httpMethod = "PUT")
  @PutMapping("/upShareCount/{id}")
  public CommonResult<Long> upShareCount(@Param(value = "视频编号") @PathVariable("id") Long id) {
    Boolean flag = wasteTurnTreasureService.updateShareCount(id);
    if (flag) {
      WasteTurnTreasure wasteTurnTreasure = wasteTurnTreasureService.getById(id);
      return resultOk(wasteTurnTreasure.getShareCount());
    }
    return resultError();
  }

  @ApiOperation(value = "用户服务-变废为宝-我的-请求发布数据")
  @GetMapping("/getListByUserIdAndAudit")
  public CommonResult<List<WasteTurnTreasure>> getListByUserIdAndAudit(
      @Param("token") @RequestHeader("token") String token,
      @Param("审核状态") @RequestParam("audit") AuditEnum auditEnum) {
    try {
      String userId = TokenUtil.toUserId(token);
      List<WasteTurnTreasure> list =
          wasteTurnTreasureService.queryListByUserIdAndAudit(userId, auditEnum);
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "用户服务-变废为宝-我的-根据标题搜索，获取信息请求", httpMethod = "GET")
  @GetMapping("/getListSearchByUserIdAndTitle/{title}")
  public CommonResult<List<WasteTurnTreasure>> getListSearchByUserIdAndTitle(
      @Param("token") @RequestHeader("token") String token,
      @Param("标题") @PathVariable("title") String title) {
    try {
      String userId = TokenUtil.toUserId(token);
      List<WasteTurnTreasure> list =
          wasteTurnTreasureService.queryListSearchByUserIdAndTitle(userId, title);
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "根据id与用户id删除变废为宝信息请求", httpMethod = "DELETE")
  @DeleteMapping("/deleteByUserIdAndId")
  public CommonResult<Boolean> deleteByUserIdAndId(
      @Param("token") @RequestHeader("token") String token,
      @Param("编号") @RequestParam("id") Long id) {
    try {
      String userId = TokenUtil.toUserId(token);
      Boolean flag = wasteTurnTreasureService.removeByUserIdAndId(userId, id);
      return resultOk(flag);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "上传图片请求", httpMethod = "POST")
  @PostMapping("/uploadImage")
  public CommonResult<String> uploadImage(
      @Param("token") @RequestHeader("token") String token,
      @Param("文件") @RequestPart("files") MultipartFile files) {
    try {
      String userId = TokenUtil.toUserId(token);
      String databasePath = uploadService.uploadImage(userId, files);
      return resultOk(databasePath);
    } catch (Exception e) {
      e.printStackTrace();
      return resultError();
    }
  }

  /**
   * 使用@RequestBody接收正文，使用实体接收url参数
   *
   * <p>接收分片数据方法
   *
   * @param file 二进制流文件，需要转为byte进行存储
   * @param wxUploadVideoInfo 文件信息
   */
  @ApiOperation(value = "上传视频请求", httpMethod = "POST")
  @PostMapping(value = "/uploadTmpVideo", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
  public CommonResult<String> uploadTmpVideo(
      @Param("token") @RequestHeader("token") String token,
      @Param("文件块内容接收对象") HttpServletRequest file,
      @Param("文件信息以及参数") WxUploadVideoInfo wxUploadVideoInfo) {
    try {
      String userId = TokenUtil.toUserId(token);
      // 接收二进制流
      InputStream bodyStream = file.getInputStream();
      // 读取流全部信息，转为字节数组
      byte[] bytes = bodyStream.readAllBytes();
      // 关闭流
      bodyStream.close();
      // 调用其他服务进行
      String result =
          uploadService.uploadTmpFile(
              bytes,
              wxUploadVideoInfo.getIdentifier(),
              wxUploadVideoInfo.getIndex(),
              wxUploadVideoInfo.getChunkSize(),
              wxUploadVideoInfo.getFileName(),
              wxUploadVideoInfo.getTotalChunks(),
              wxUploadVideoInfo.getTotalSize(),
              userId);
      return resultOk(result);
    } catch (Exception e) {
      return resultError();
    }
  }

  /**
   * 合并分块请求
   *
   * @param identifier md5值
   * @param fileName 文件名
   * @return 线上数据库文件路径
   */
  @ApiOperation(value = "合并上传文件请求", httpMethod = "GET")
  @GetMapping("/mergeTmpFile")
  public CommonResult<String> mergeTmpFile(
      @Param("md5值") @RequestParam("identifier") String identifier,
      @Param("文件名") @RequestParam("fileName") String fileName) {
    try {
      String result = uploadService.mergeTmpFile(identifier, fileName);
      return resultOk(result);
    } catch (Exception e) {
      e.printStackTrace();
      return resultError();
    }
  }

  @ApiOperation(value = "插入变废为宝信息", httpMethod = "POST")
  @PostMapping("/postUserWasteInfo")
  public CommonResult<Boolean> postUserWasteInfo(
      @Param("token") @RequestHeader("token") String token,
      @Param("变废为宝基本信息") @RequestBody WasteTurnTreasure wasteTurnTreasure) {
    try {
      String userId = TokenUtil.toUserId(token);
      wasteTurnTreasure.setPromulgatorId(userId);
      System.out.println(wasteTurnTreasure);
      boolean save = wasteTurnTreasureService.createUserWasteInfo(wasteTurnTreasure);
      if (save) {
        return resultOk(true);
      }
    } catch (Exception e) {
      return resultError();
    }
    return resultError();
  }

  @ApiOperation(value = "根据id请求变废为宝信息", httpMethod = "GET")
  @GetMapping("/getById")
  public CommonResult<WasteTurnTreasure> getById(@Param("变废为宝信息编号") @RequestParam("id") Long id) {
    try {
      WasteTurnTreasure wasteTurnTreasure = wasteTurnTreasureService.getById(id);
      return resultOk(wasteTurnTreasure);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "更新变废为宝信息", httpMethod = "PUT")
  @PutMapping("/putUserWasteInfo")
  public CommonResult<Boolean> putUserWasteInfo(
      @Param("token") @RequestHeader("token") String token,
      @Param("变废为宝基本信息") @RequestBody WasteTurnTreasure wasteTurnTreasure) {
    try {
      String userId = TokenUtil.toUserId(token);
      wasteTurnTreasure.setAudit(AuditEnum.TO_AUDIT);
      if (!userId.equals(wasteTurnTreasure.getPromulgatorId())) {
        return resultError();
      }
      boolean save = wasteTurnTreasureService.updateUserWasteInfo(wasteTurnTreasure);
      if (save) {
        return resultOk(true);
      }
    } catch (Exception e) {
      return resultError();
    }
    return resultError();
  }
}
