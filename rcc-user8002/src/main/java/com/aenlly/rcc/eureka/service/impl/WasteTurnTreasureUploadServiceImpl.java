package com.aenlly.rcc.eureka.service.impl;

import com.aenlly.rcc.eureka.service.IWasteTurnTreasureUploadService;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Aenlly
 * @create by date 2021/12/19 18:41
 * @projectName RefuseClassificationCultivate
 */
@Component()
public class WasteTurnTreasureUploadServiceImpl implements IWasteTurnTreasureUploadService {
  @Override
  public String UploadImage(String userId, MultipartFile file) {
    System.out.println("异常");
    return "212";
  }
}
