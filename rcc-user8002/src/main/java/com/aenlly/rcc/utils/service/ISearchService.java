package com.aenlly.rcc.utils.service;

import com.aenlly.rcc.entity.SearchLibrary;
import com.aenlly.rcc.entity.SearchUser;
import com.aenlly.rcc.enums.SearchTypeEnum;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

/**
 * @author Aenlly
 * @create by date 2021/12/14 16:35
 * @projectName RefuseClassificationCultivate
 */
public interface ISearchService {

  /**
   * 垃圾类型文本搜索
   *
   * @param name 垃圾名称
   * @return 搜索的垃圾所属类型集合
   */
  Collection<SearchLibrary> searchText(String name, String userId, SearchTypeEnum searchTypeEnum);

  /**
   * 垃圾类型语音搜索
   *
   * @param voice 语音文件，mp3格式
   * @return 语音识别结果
   */
  String searchVoice(MultipartFile voice);

  /**
   * 垃圾类型图像识别搜索
   *
   * @param picture 图片文件
   * @return 图像识别结果
   */
  String searchPicture(MultipartFile picture);

  /**
   * 查询用户搜索记录
   *
   * @param userId 用户编号
   * @return 搜索记录列表
   */
  List<SearchUser> getSearchList(String userId);

  /**
   * 查询用户搜索记录
   *
   * @param userId 用户编号
   * @param name 搜索名称
   * @return 结果集
   */
  List<SearchUser> getSearchByName(String userId, String name);
}
