package com.aenlly.rcc.utils.impl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.aenlly.rcc.entity.GarbageLibrary;
import com.aenlly.rcc.entity.UserSearch;
import com.aenlly.rcc.service.IGarbageLibraryService;
import com.aenlly.rcc.service.IUserSearchService;
import com.aenlly.rcc.utils.GarbageLibraryAdd;
import com.aenlly.rcc.utils.SearchService;
import com.aenlly.rcc.utils.wrapper.QueryWrapperUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.*;

import static com.aenlly.rcc.utils.SearchApi.TEXT_API;
import static com.aenlly.rcc.utils.SearchType.SEARCH_TEXT;

/**
 * @author Aenlly
 * @create by date 2021/12/14 16:34
 * @projectName RefuseClassificationCultivate
 */
@Service
public class SearchServiceImpl implements SearchService {
  /** 垃圾分类库的服务 */
  @Resource private IGarbageLibraryService garbageLibraryService;
  /** http请求对象 */
  @Resource private RestTemplate template;
  /** 用户搜索服务对象 */
  @Resource private IUserSearchService userSearchService;
  /** 插入数据库未拥有的数据对象 */
  @Resource private GarbageLibraryAdd garbageLibraryAdd;

  /**
   * 垃圾类型文本搜索
   *
   * @param name 垃圾名称
   * @param userId 用户id
   * @return 搜索的垃圾所属类型集合
   */
  @Override
  public Collection<GarbageLibrary> searchText(String name, String userId) {
    // 增加搜索记录
    if (StringUtils.isNotBlank(userId)) {
      UserSearch userSearch = new UserSearch(name, SEARCH_TEXT.getValue(), userId);
      boolean save = userSearchService.save(userSearch);
      if (!save) {
        return null;
      }
    }
    // 下列进行搜索，单个查询，然后进行去重
    char[] chars = name.toCharArray();
    Map<Integer, GarbageLibrary> map = new HashMap<>();
    for (char c : chars) {
      Wrapper<GarbageLibrary> queryWrapper = QueryWrapperUtil.getSearchText(c);
      List<GarbageLibrary> list = garbageLibraryService.list(queryWrapper);
      for (GarbageLibrary garbageLibrary : list) {
        map.put(garbageLibrary.getId(), garbageLibrary);
      }
    }
    // 在服务器库中存在垃圾分类的记录，则返回
    if (map.values().size() > 0) {
      return map.values();
    }
    // 在服务器库中未找到记录，则进行第三方api查询
    return transferTextApi(name);
  }

  /**
   * 垃圾类型语音搜索
   *
   * @return 搜索的垃圾所属类型集合
   */
  @Override
  public Collection<GarbageLibrary> searchVoice() {
    return null;
  }

  /**
   * 垃圾类型图像识别搜索
   *
   * @return 搜索的垃圾所属类型集合
   */
  @Override
  public Collection<GarbageLibrary> searchPicture() {
    return null;
  }

  /**
   * 查询用户搜索记录
   *
   * @param userId 用户编号
   * @return 搜索记录列表
   */
  @Override
  public List<UserSearch> getSearchList(String userId) {
    isNotUserId(userId);
    Wrapper<UserSearch> queryWrapper = QueryWrapperUtil.getSearchList(userId);
    return userSearchService.list(queryWrapper);
  }

  /**
   * 查询用户搜索记录
   *
   * @param userId 用户编号
   * @param name 搜索名称
   * @return 结果集
   */
  @Override
  public List<UserSearch> getSearchByName(String userId, String name) {
    isNotUserId(userId);
    if (StringUtils.isNotBlank(name)) {
      Wrapper<UserSearch> queryWrapper = QueryWrapperUtil.getSearchByName(userId, name);
      return userSearchService.list(queryWrapper);
    } else {
      return getSearchList(userId);
    }
  }

  /**
   * 判断用户编号是否为空 为空则抛出异常
   *
   * @param userId 用户编号
   */
  public void isNotUserId(String userId) {
    if (!StringUtils.isNotBlank(userId)) {
      // 抛出空异常
      throw new NullPointerException();
    }
  }

  /**
   * 调用文本搜索垃圾分类API
   *
   * @param name 垃圾名称
   * @return 集合
   */
  public Collection<GarbageLibrary> transferTextApi(String name) {
    List<GarbageLibrary> list = new ArrayList<>();
    // 拼接url,使用的api天行
    // TODO:更换调用API：TEXT_API.getValue()：http://api.tianapi.com/lajifenlei/index?key=接口key&word=%s
    String format = String.format(TEXT_API.getValue(), name);
    String result = template.getForObject(format, String.class);
    JSONObject json = JSONUtil.parseObj(result);
    if (json.containsKey("code")) {
      if (json.get("code", Integer.class) == 200) {
        JSONArray jsonArray = json.getJSONArray("newslist");
        if (jsonArray != null) {
          for (Object o : jsonArray) {
            JSONObject next = (JSONObject) o;
            String name1 = next.get("name", String.class);
            Integer type = next.get("type", Integer.class);
            // 0为可回收垃圾，1为有害垃圾，2为厨余垃圾(湿垃圾)，3为其他垃圾(干垃圾)
            GarbageLibrary library = new GarbageLibrary();
            library.setName(name1);
            switch (type) {
              case 0:
                library.setType("可回收垃圾");
                break;
              case 1:
                library.setType("有害垃圾");
                break;
              case 2:
                library.setType("厨余垃圾");
                break;
              case 3:
                library.setType("其他垃圾");
                break;
            }
            list.add(library);
          }
        }
        garbageLibraryAdd.garbageLibraryAdd(list);

        return list;
      }
    }
    return null;
  }
}
