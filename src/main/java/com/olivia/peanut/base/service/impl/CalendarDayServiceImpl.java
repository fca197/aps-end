package com.olivia.peanut.base.service.impl;

import com.github.yulichang.base.MPJBaseServiceImpl;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.base.mapper.CalendarDayMapper;
import com.olivia.peanut.base.model.CalendarDay;
import com.olivia.peanut.base.service.CalendarDayService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 工作日历详情(CalendarDay)表服务实现类
 *
 * @author makejava
 * @since 2024-03-11 10:44:03
 */
@Service("calendarDayService")
@Transactional
public class CalendarDayServiceImpl extends MPJBaseServiceImpl<CalendarDayMapper, CalendarDay> implements CalendarDayService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

}

