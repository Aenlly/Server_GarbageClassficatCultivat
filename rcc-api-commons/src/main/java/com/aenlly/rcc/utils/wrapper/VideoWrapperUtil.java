package com.aenlly.rcc.utils.wrapper;

import com.aenlly.rcc.entity.Video;
import com.aenlly.rcc.enums.VideoCheckEnum;
import com.aenlly.rcc.utils.enums.QueryVideoType;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;

/**
 * @author Aenlly
 * @create by date 2022/01/11 18:20
 * @projectName RefuseClassificationCultivate
 */
public class VideoWrapperUtil {

  /**
   * 根据查询条件获取操作对象
   *
   * @param queryType 查询字段
   * @param text 查询内容
   * @return 查询对象
   */
  public static Wrapper<Video> queryVideoListPage(QueryVideoType queryType, String text) {
    QueryWrapper<Video> wrapper = new QueryWrapper<>();
    wrapper
        .like(queryType.getValue(), text)
        .orderByDesc("video_check")
        .orderBy(true, false, "update_time");
    return wrapper;
  }

  /**
   * 根据编号修改视频状态，获取该操作对象
   *
   * @param id 编号
   * @param videoCheckEnum 视频状态
   * @return 更新对象
   */
  public static Wrapper<Video> updateVideoByIdPutCheck(Long id, VideoCheckEnum videoCheckEnum) {
    UpdateWrapper<Video> wrapper = new UpdateWrapper<>();
    wrapper.set("video_check", videoCheckEnum).eq("video_id", id);
    return wrapper;
  }

  /**
   * 构造使置顶信息，更新为非置顶吗，获取操作对象
   *
   * @param videoCheckEnum 视频状态条件
   * @return 更新对象
   */
  public static Wrapper<Video> updateVideoByCheck(VideoCheckEnum videoCheckEnum) {
    UpdateWrapper<Video> wrapper = new UpdateWrapper<>();
    wrapper.set("video_check", VideoCheckEnum.PUBLISH_OK).eq("video_check", videoCheckEnum);
    return wrapper;
  }
}
