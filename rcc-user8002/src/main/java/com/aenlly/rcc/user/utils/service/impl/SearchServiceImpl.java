package com.aenlly.rcc.user.utils.service.impl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.aenlly.rcc.entity.SearchLibrary;
import com.aenlly.rcc.entity.SearchUser;
import com.aenlly.rcc.enums.GarbageTypeEnum;
import com.aenlly.rcc.enums.SearchTypeEnum;
import com.aenlly.rcc.service.ISearchLibraryService;
import com.aenlly.rcc.service.ISearchUserService;
import com.aenlly.rcc.user.utils.service.ISearchService;
import com.aenlly.rcc.user.utils.service.SearchLibraryAdd;
import com.aenlly.rcc.utils.AuthService;
import com.aenlly.rcc.utils.baidu.Base64Util;
import com.aenlly.rcc.utils.baidu.HttpUtil;
import com.aenlly.rcc.utils.wrapper.SearchWrapperUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.tencentcloudapi.asr.v20190614.AsrClient;
import com.tencentcloudapi.asr.v20190614.models.SentenceRecognitionRequest;
import com.tencentcloudapi.asr.v20190614.models.SentenceRecognitionResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import lombok.SneakyThrows;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static com.aenlly.rcc.utils.SearchApi.*;

/**
 * @author Aenlly
 * @create by date 2021/12/14 16:34
 * @projectName RefuseClassificationCultivate
 */
@Service
public class SearchServiceImpl implements ISearchService {
  /** 垃圾分类库的服务 */
  @Resource private ISearchLibraryService searchLibraryService;
  /** http请求对象 */
  @Resource private RestTemplate template;
  /** 用户搜索服务对象 */
  @Resource private ISearchUserService searchUserService;
  /** 插入数据库未拥有的数据对象 */
  @Resource private SearchLibraryAdd searchLibraryAdd;

  /**
   * 垃圾类型文本搜索
   *
   * @param name 垃圾名称
   * @param userId 用户id
   * @param searchTypeEnum 搜索类型
   * @return 搜索的垃圾所属类型集合
   */
  @Override
  public Collection<SearchLibrary> searchText(
      String name, String userId, SearchTypeEnum searchTypeEnum) {
    // 增加搜索记录
    if (StringUtils.isNotBlank(userId)) {
      SearchUser searchUser = new SearchUser(name, searchTypeEnum, userId);
      boolean save = searchUserService.save(searchUser);
      if (!save) {
        return null;
      }
    }
    // 下列进行搜索，单个查询，然后进行去重
    char[] chars = name.toCharArray();
    Map<Integer, SearchLibrary> map = new HashMap<>();
    for (char c : chars) {
      Wrapper<SearchLibrary> queryWrapper = SearchWrapperUtil.getSearchText(c);
      List<SearchLibrary> list = searchLibraryService.list(queryWrapper);
      for (SearchLibrary searchLibrary : list) {
        map.put(searchLibrary.getId(), searchLibrary);
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
   * @param voice 语音文件，mp3格式
   * @return 搜索的垃圾所属类型集合
   */
  @SneakyThrows
  @Override
  public String searchVoice(MultipartFile voice) {
    // 获得语音全部字节数组
    byte[] bytes = voice.getBytes();
    // 转码
    String data = Base64.getEncoder().encodeToString(bytes);
    // 调用执行api方法获取结果
    return transferVoiceApi(data, (long) bytes.length);
  }

  /**
   * 垃圾类型图像识别搜索
   *
   * @param picture 图片文件
   * @return 图像识别结果
   */
  @Override
  public String searchPicture(MultipartFile picture) {
    return transferPictureApi(picture);
  }

  /**
   * 查询用户搜索记录
   *
   * @param userId 用户编号
   * @return 搜索记录列表
   */
  @Override
  public List<SearchUser> getSearchList(String userId) {
    isNotUserId(userId);
    Wrapper<SearchUser> queryWrapper = SearchWrapperUtil.getSearchList(userId);
    return searchUserService.list(queryWrapper);
  }

  /**
   * 查询用户搜索记录
   *
   * @param userId 用户编号
   * @param name 搜索名称
   * @return 结果集
   */
  @Override
  public List<SearchUser> getSearchByName(String userId, String name) {
    isNotUserId(userId);
    if (StringUtils.isNotBlank(name)) {
      Wrapper<SearchUser> queryWrapper = SearchWrapperUtil.getSearchByName(userId, name);
      return searchUserService.list(queryWrapper);
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
  public Collection<SearchLibrary> transferTextApi(String name) {
    List<SearchLibrary> list = new ArrayList<>();
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
            SearchLibrary library = new SearchLibrary();
            library.setName(name1);
            switch (type) {
              case 0:
                library.setType(GarbageTypeEnum.RECYCLABLE);
                break;
              case 1:
                library.setType(GarbageTypeEnum.HARMFUL);
                break;
              case 2:
                library.setType(GarbageTypeEnum.KITCHEN);
                break;
              case 3:
                library.setType(GarbageTypeEnum.OTHER);
                break;
            }
            list.add(library);
          }
        }
        searchLibraryAdd.searchLibraryAdd(list);

        return list;
      }
    }
    return null;
  }

  /**
   * 调用语音搜索api
   *
   * @param data 语音数据，要使用base64编码
   * @param dataLen 语音数据长度
   * @return 识别结果
   */
  @SneakyThrows
  public String transferVoiceApi(String data, Long dataLen) {
    // 拼接url,使用腾讯一句话的api
    // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey,此处还需注意密钥对的保密
    // 密钥可前往https://console.cloud.tencent.com/cam/capi网站进行获取
    // TODO:更换调用API的ID与KEY
    Credential cred = new Credential(VOICE_TX_SECRET_ID.getValue(), VOICE_TX_SECRET_KET.getValue());
    // 实例化一个http选项，可选的，没有特殊需求可以跳过
    HttpProfile httpProfile = new HttpProfile();
    // 调用地址：asr.tencentcloudapi.com
    httpProfile.setEndpoint(VOICE_TX_API_URL.getValue());
    // 实例化一个client选项，可选的，没有特殊需求可以跳过
    ClientProfile clientProfile = new ClientProfile();
    clientProfile.setHttpProfile(httpProfile);
    // 实例化要请求产品的client对象,clientProfile是可选的
    AsrClient client = new AsrClient(cred, "", clientProfile);

    // VOICE_TX_STRING(
    //         "{"
    //                 + "\"ProjectId\": 0,"  腾讯云项目 ID，可填 0，总长度不超过 1024 字节。
    //                 + "\"SubServiceType\": 2," 子服务类型。2： 一句话识别。
    //                 + "\"EngSerViceType\": \"16k_zh_dialect\","  引擎模型类型。 16k_zh_dialect：16k 多方言；
    //                 + "\"SourceType\": 1," 语音数据来源。0：语音 URL；1：语音数据（post body）。
    //                 + "\"VoiceFormat\": \"mp3\"," 识别音频的音频格式。mp3、wav。
    //                 + "\"UsrAudioKey\": %s"  	用户端对此任务的唯一标识，用户自助生成，用于用户查找识别结果。
    //                 + "}");
    // 唯一请求 ID
    String UsrAudioKey = String.valueOf(UUID.randomUUID().getLeastSignificantBits());
    // 请求参数
    String reqData = String.format(VOICE_TX_STRING.getValue(), UsrAudioKey);

    // 实例化一个请求对象,每个接口都会对应一个request对象
    SentenceRecognitionRequest req =
        SentenceRecognitionRequest.fromJsonString(reqData, SentenceRecognitionRequest.class);

    // 设置语音数据长度
    req.setDataLen(dataLen);
    // 设置语音数据
    req.setData(data);
    // 返回的resp是一个SentenceRecognitionResponse的实例，与请求对象对应
    SentenceRecognitionResponse resp = client.SentenceRecognition(req);
    // 获得识别结果
    String result = resp.getResult();
    // 返去掉结束标点的字符串
    return result.split("。")[0];
  }

  private String transferPictureApi(MultipartFile picture) {
    try {
      // 获取文件内容，转为字节数组
      byte[] imgData = picture.getBytes();
      // 转换编码成api所需编码字符串
      String imgStr = Base64Util.encode(imgData);
      // 转换成url编码
      String imgParam = URLEncoder.encode(imgStr, StandardCharsets.UTF_8);

      String param = "image=" + imgParam;

      // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
      // AuthService类参考：https://cloud.baidu.com/doc/IMAGERECOGNITION/s/7k3bcxdn8中的方法一
      String accessToken = AuthService.getAuth();
      // 调用接口
      String json = HttpUtil.post(PICTURE_BAIDU_API_URL.getValue(), accessToken, param);
      // 转换结果格式
      JSONObject jsonObject = JSONUtil.parseObj(json);
      // 获得结果集数量
      Integer result_num = jsonObject.get("result_num", Integer.class);
      if (result_num > 0) {
        // 获得结果集
        JSONArray resultList = jsonObject.get("result", JSONArray.class);
        // 取识别第一位
        JSONObject result = resultList.get(0, JSONObject.class);
        // 返回识别结果
        return result.get("keyword", String.class);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    throw new NullPointerException();
  }
}
