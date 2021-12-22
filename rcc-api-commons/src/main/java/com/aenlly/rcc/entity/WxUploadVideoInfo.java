package com.aenlly.rcc.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 微信分块上传的信息接收实体
 *
 * @author Aenlly
 * @create by date 2021/12/22 21:41
 * @projectName RefuseClassificationCultivate
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "分块上传文件对象", description = "用于存储上传分块文件的实体类")
public class WxUploadVideoInfo {
  /** 文件的唯一标识 */
  private String identifier;
  /** 分块的序号，从 0 开始 */
  private Long index;
  /** 分块大小，最后一块可能小于该值 */
  private Long chunkSize;
  /** 文件名，传入的文件名 */
  private String fileName;
  /** 分块的总数量，依据 chunkSize 计算 */
  private Long totalChunks;
  /** 文件总大小 */
  private Long totalSize;
  /** 用户编号 */
  private String userId;
}
