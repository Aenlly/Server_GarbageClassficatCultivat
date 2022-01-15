package com.aenlly.rcc.user.service.impl;

import com.aenlly.rcc.entity.Gift;
import com.aenlly.rcc.entity.GiftInfo;
import com.aenlly.rcc.entity.User;
import com.aenlly.rcc.enums.GiftStateEnum;
import com.aenlly.rcc.mapper.GiftMapper;
import com.aenlly.rcc.user.service.*;
import com.aenlly.rcc.utils.RedemptionCodeUtil;
import com.aenlly.rcc.utils.wrapper.GiftWrapperUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 礼品信息表 服务实现类
 *
 * @author aenlly
 * @since 2021-12-25
 */
@Slf4j
@Service
public class GiftServiceImpl extends ServiceImpl<GiftMapper, Gift> implements IGiftService {

  /** 礼品信息详情表服务对象 */
  @Resource private IGiftInfoService giftInfoService;

  /** 用户信息服务对象 */
  @Resource private IUserService userService;

  /** 积分记录服务对象 */
  @Resource private IPointsLogService pointsLogService;

  /** 订单记录服务对象 */
  @Resource private IOrdersService ordersService;

  // private final AtomicLong atomicLong = new AtomicLong();

  /**
   * 兑换服务
   *
   * <p>IndexOutOfBoundsException()异常代表积分不足
   *
   * <p>NullPointerException()异常代表部分过程错误
   *
   * @param userId 用户编号
   * @param id 礼品id
   * @return 兑换码
   */
  @Override
  @Transactional
  public String convertById(String userId, Long id) {
    Gift gift = baseMapper.selectById(id);
    User user = userService.getById(userId);
    // 判断积分是否充足
    if (user.getRemainingPoints() < gift.getPoint()) {
      throw new IndexOutOfBoundsException();
    }
    Wrapper<GiftInfo> wrapper = GiftWrapperUtil.getGiftInfoByNotSold(id);
    // 获取礼品单一详细对象
    GiftInfo giftInfo = giftInfoService.getOne(wrapper);
    // 生成兑换码
    String code = RedemptionCodeUtil.generateShortUuid();
    // 设置传输
    giftInfo.setState(GiftStateEnum.SOLD);
    giftInfo.setCode(code);
    // 更新数据
    giftInfoService.updateById(giftInfo);
    // 兑换后的剩余积分
    Boolean reduce = userService.updatePointReduce(user, gift.getPoint());
    // 积分记录写入
    boolean giftLog = pointsLogService.giftLog(userId, gift.getPoint(), "兑换" + gift.getGiftName());

    // 插入订单记录
    Boolean save = ordersService.save(userId, giftInfo.getId());
    if (!reduce || !giftLog || !save) {
      throw new NullPointerException();
    }
    return code;
  }
}
