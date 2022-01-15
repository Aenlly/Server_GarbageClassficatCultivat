package com.aenlly.rcc.user.service.impl;

import com.aenlly.rcc.entity.CarouselUserView;
import com.aenlly.rcc.mapper.CarouselUserViewMapper;
import com.aenlly.rcc.user.service.ICarouselUserViewService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * VIEW 服务实现类
 *
 * @author aenlly
 * @since 2021-12-11
 */
@Service
public class CarouselUserViewServiceImpl
    extends ServiceImpl<CarouselUserViewMapper, CarouselUserView>
    implements ICarouselUserViewService {}
