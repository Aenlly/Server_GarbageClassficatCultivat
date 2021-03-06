package com.aenlly.rcc.utils.wrapper;

import com.aenlly.rcc.entity.Video;
import com.aenlly.rcc.entity.VideoUserView;
import com.aenlly.rcc.enums.VideoCheckEnum;
import com.aenlly.rcc.utils.enums.QueryVideoTypeEnum;
import com.aenlly.rcc.vo.VideoVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.lang3.StringUtils;

import static com.aenlly.rcc.utils.enums.WrapperFiledConstant.UPDATE_TIME_FILED;

/**
 @author Aenlly
 @create by date 2022/01/11 18:20
 @projectName RefuseClassificationCultivate */
public class VideoWrapperUtil {

    /**
     根据查询条件获取操作对象

     @param queryType 查询字段
     @param text      查询内容
     @return 查询对象
     */
    public static Wrapper<VideoVo> queryVideoListPage(QueryVideoTypeEnum queryType, String text) {
        QueryWrapper<VideoVo> wrapper = new QueryWrapper<>();
        wrapper.like(queryType.getValue(), text)
               .eq("delete_flag", 0)
               .orderByDesc("video_check")
               .orderByDesc(UPDATE_TIME_FILED);
        return wrapper;
    }

    /**
     根据编号修改视频状态，获取该操作对象

     @param id             编号
     @param videoCheckEnum 视频状态
     @return 更新对象
     */
    public static Wrapper<Video> updateVideoByIdPutCheck(Long id, VideoCheckEnum videoCheckEnum) {
        UpdateWrapper<Video> wrapper = new UpdateWrapper<>();
        wrapper.set("video_check", videoCheckEnum)
               .eq("video_id", id);
        return wrapper;
    }

    /**
     构造使置顶信息，更新为非置顶吗，获取操作对象

     @param videoCheckEnum 视频状态条件
     @return 更新对象
     */
    public static Wrapper<Video> updateVideoByCheck(VideoCheckEnum videoCheckEnum) {
        UpdateWrapper<Video> wrapper = new UpdateWrapper<>();
        wrapper.set("video_check", VideoCheckEnum.PUBLISH_OK)
               .eq("video_check", videoCheckEnum);
        return wrapper;
    }

    /**
     用户服务-公益视频根据标题获得操作对象

     @param title 标题
     @return 查询对象
     */
    public static Wrapper<VideoUserView> getVideoByTitleList(String title) {
        QueryWrapper<VideoUserView> wrapper = new QueryWrapper<>();
        wrapper.in("video_check", VideoCheckEnum.PUBLISH_OK, VideoCheckEnum.TOP)
               .orderByDesc(UPDATE_TIME_FILED);
        if (StringUtils.isNotBlank(title)) {
            wrapper.likeRight("video_title", title);
        }
        return wrapper;
    }
}
