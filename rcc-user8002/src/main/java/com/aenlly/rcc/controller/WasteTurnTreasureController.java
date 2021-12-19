package com.aenlly.rcc.controller;

import com.aenlly.rcc.entity.User;
import com.aenlly.rcc.entity.WasteTurnTreasure;
import com.aenlly.rcc.enums.AuditEnum;
import com.aenlly.rcc.enums.UserUploadEnum;
import com.aenlly.rcc.enums.WasteTagEnum;
import com.aenlly.rcc.eureka.service.IWasteTurnTreasureUploadService;
import com.aenlly.rcc.service.IUserService;
import com.aenlly.rcc.service.IWasteTurnTreasureService;
import com.aenlly.rcc.utils.CommonResult;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
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
@RequestMapping("/waste-turn-treasure")
public class WasteTurnTreasureController {

  /** 变废为宝表-服务对象 */
  @Resource IWasteTurnTreasureService wasteTurnTreasureService;
  /** 变废为宝上传服务调用接口 */
  @Resource IWasteTurnTreasureUploadService wasteTurnTreasureUploadService;

  @Resource IUserService userService;

  @ApiOperation(value = "用户服务-变废为宝-初始请求-根据标签搜索，获取信息请求", httpMethod = "GET")
  @GetMapping("/getListByTag/{tag}")
  @JsonCreator
  public CommonResult<List<WasteTurnTreasure>> getListByTag(
      @Param("标签") @PathVariable("tag") WasteTagEnum wasteTagEnum) {
    try {
      List<WasteTurnTreasure> list = wasteTurnTreasureService.getListByTag(wasteTagEnum);
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
      List<WasteTurnTreasure> list = wasteTurnTreasureService.getListSearchByTitle(title);
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
      if (wasteTurnTreasure.getIsUserUpload().equals(UserUploadEnum.YES)) {
        User user = userService.getNameAndAvatarById(wasteTurnTreasure.getUserId());
        wasteTurnTreasure.setUser(user);
      }
      return resultOk(wasteTurnTreasure);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "分享操作，用户通过分享的链接进入时，分享数量增加", httpMethod = "PUT")
  @PutMapping("/upShareCount/{id}")
  public CommonResult<Long> upShareCount(@Param(value = "视频编号") @PathVariable("id") Long id) {
    Boolean flag = wasteTurnTreasureService.upShareCount(id);
    if (flag) {
      WasteTurnTreasure wasteTurnTreasure = wasteTurnTreasureService.getById(id);
      return resultOk(wasteTurnTreasure.getShareCount());
    }
    return resultError();
  }

  @ApiOperation(value = "用户服务-变废为宝-我的-请求发布数据")
  @GetMapping("/getListByUserIdAndAudit")
  public CommonResult<List<WasteTurnTreasure>> getListByUserIdAndAudit(
      @Param("用户编号") @RequestParam("userId") String userId,
      @Param("审核状态") @RequestParam("audit") AuditEnum auditEnum) {
    try {
      List<WasteTurnTreasure> list =
          wasteTurnTreasureService.getListByUserIdAndAudit(userId, auditEnum);
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "用户服务-变废为宝-我的-根据标题搜索，获取信息请求", httpMethod = "GET")
  @GetMapping("/getListSearchByUserIdAndTitle/{userId}/{title}")
  public CommonResult<List<WasteTurnTreasure>> getListSearchByUserIdAndTitle(
      @Param("用户编号") @PathVariable("userId") String userId,
      @Param("标题") @PathVariable("title") String title) {
    try {
      List<WasteTurnTreasure> list =
          wasteTurnTreasureService.getListSearchByUserIdAndTitle(userId, title);
      return resultOk(list);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "根据id与用户id删除变废为宝信息请求", httpMethod = "DELETE")
  @DeleteMapping("/deleteByUserIdAndId")
  public CommonResult<Boolean> deleteByUserIdAndId(
      @Param("用户编号") @RequestParam("userId") String userId,
      @Param("编号") @RequestParam("id") Long id) {
    try {
      Boolean flag = wasteTurnTreasureService.removeByUserIdAndId(userId, id);
      return resultOk(flag);
    } catch (Exception e) {
      return resultError();
    }
  }

  @ApiOperation(value = "上传图片请求", httpMethod = "POST")
  @PostMapping("/uploadImage")
  public CommonResult<String> uploadImage(
      @Param("用户编号") @RequestParam("userId") String userId,
      @Param("文件") @RequestPart("files") MultipartFile files) {
    try {
      String databasePath = wasteTurnTreasureUploadService.UploadImage(userId, files);
      return resultOk(databasePath);
    } catch (Exception e) {
      return resultError();
    }
  }
}
