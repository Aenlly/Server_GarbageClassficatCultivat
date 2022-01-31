package com.aenlly.rcc.utils.wrapper;

import com.aenlly.rcc.entity.WasteTurnTreasure;
import com.aenlly.rcc.enums.AuditEnum;
import com.aenlly.rcc.enums.IsUserUploadEnum;
import com.aenlly.rcc.enums.WasteTagEnum;
import com.aenlly.rcc.utils.enums.QueryWasteTypeEnum;
import com.aenlly.rcc.vo.WasteTurnTreasureVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;

import java.util.List;

/**
 * 管理员变废为宝获取实体操作工具类
 *
 * @author Aenlly
 * @create by date 2022/01/31 16:17
 * @projectName RefuseClassificationCultivate
 */
public class WasteTurnTreasureWrapperUtil {

  /**
   * 根据条件获取实体操作对象
   *
   * @param queryType 查询条件
   * @param text 查询内容
   * @param textTag 标签条件，可为null
   * @param isUserUploadEnum 是否用户上传
   * @param auditEnums 审核状态
   * @return 查询对象
   */
  public static Wrapper<WasteTurnTreasureVo> queryListPage(
      QueryWasteTypeEnum queryType,
      String text,
      WasteTagEnum textTag,
      IsUserUploadEnum isUserUploadEnum,
      List<AuditEnum> auditEnums) {
    QueryWrapper<WasteTurnTreasureVo> wrapper = new QueryWrapper<>();
    wrapper
        .like(queryType.getValue(), text)
        .eq("is_user_upload", isUserUploadEnum)
        .eq("delete_flag", 0)
        .in("audit", auditEnums);
    if (textTag != null) {
      wrapper.eq("text_tag", textTag);
    }
    return wrapper;
  }

  /**
   * 根据编号修改审核状态，获取该操作对象
   *
   * @param id 编号
   * @param audit 审核状态
   * @return 更新对象
   */
  public static Wrapper<WasteTurnTreasure> updateByIdPutAudit(Long id, AuditEnum audit) {
    UpdateWrapper<WasteTurnTreasure> wrapper = new UpdateWrapper<>();
    wrapper.set("audit", audit).eq("id", id);
    return wrapper;
  }
}
