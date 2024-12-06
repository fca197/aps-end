package com.olivia.peanut.portal.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReflectUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.con.ApsStr;
import com.olivia.peanut.portal.api.entity.calendar.*;
import com.olivia.peanut.portal.mapper.CalendarMapper;
import com.olivia.peanut.portal.model.Calendar;
import com.olivia.peanut.portal.model.CalendarDay;
import com.olivia.peanut.portal.service.CalendarDayService;
import com.olivia.peanut.portal.service.CalendarService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.BaseEntity;
import com.olivia.sdk.utils.DateUtils;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.model.WeekInfo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.olivia.sdk.utils.FieldUtils.getField;
import static java.lang.Boolean.TRUE;

/**
 * 工作日历(Calendar)表服务实现类
 *
 * @author makejava
 * @since 2024-03-11 10:44:03
 */
@Slf4j
@Service("calendarService")
@Transactional
public class CalendarServiceImpl extends MPJBaseServiceImpl<CalendarMapper, Calendar> implements CalendarService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();
  @Resource
  CalendarDayService calendarDayService;

  public @Override CalendarQueryListRes queryList(CalendarQueryListReq req) {

    MPJLambdaWrapper<Calendar> q = getWrapper(req.getData());
    List<Calendar> list = this.list(q);

    List<CalendarDto> dataList = list.stream().map(t -> $.copy(t, CalendarDto.class)).collect(Collectors.toList());

    return new CalendarQueryListRes().setDataList(dataList);
  }

  // 以下为私有对象封装

  public @Override DynamicsPage<CalendarExportQueryPageListInfoRes> queryPageList(CalendarExportQueryPageListReq req) {

    DynamicsPage<Calendar> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<Calendar> q = getWrapper(req.getData());
    List<CalendarExportQueryPageListInfoRes> records;
    if (TRUE.equals(req.getQueryPage())) {
      IPage<Calendar> list = this.page(page, q);
      IPage<CalendarExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, CalendarExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), CalendarExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作
    List<CalendarExportQueryPageListInfoRes> listInfoRes = $.copyList(records, CalendarExportQueryPageListInfoRes.class);

    return DynamicsPage.init(page, listInfoRes);
  }

  private MPJLambdaWrapper<Calendar> getWrapper(CalendarDto obj) {
    MPJLambdaWrapper<Calendar> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q.eq(Objects.nonNull(obj.getId()), Calendar::getId, obj.getId()).eq(Objects.nonNull(obj.getTenantId()), Calendar::getTenantId, obj.getTenantId())
          .eq(Objects.nonNull(obj.getFactoryId()), Calendar::getFactoryId, obj.getFactoryId())
          .eq(StringUtils.isNoneBlank(obj.getCalendarName()), Calendar::getCalendarName, obj.getCalendarName())
          .eq(StringUtils.isNoneBlank(obj.getCalendarCode()), Calendar::getCalendarCode, obj.getCalendarCode())
          .eq(StringUtils.isNoneBlank(obj.getCalendarDesc()), Calendar::getCalendarDesc, obj.getCalendarDesc())
          .eq(Objects.nonNull(obj.getCalendarDisabled()), Calendar::getCalendarDisabled, obj.getCalendarDisabled());
    }

    return q;

  }

  private void setQueryListHeader(DynamicsPage<Calendar> page) {
    page.addHeader("id", "id").addHeader("tenantId", "所属租户id").addHeader(ApsStr.FACTORY_ID, "所属工厂id").addHeader("calendarName", "日历名称").addHeader("calendarCode", "日历编码")
        .addHeader("calendarType", "日历类型 ").addHeader("calendarDesc", "日历描述").addHeader("calendarDisabled", "日历状态 0 启用 ,1 禁用  ")
        .addHeader("isDelete", "是否删除(0:否 1:是)").addHeader("createTime", "创建时间").addHeader("createBy", "创建人id").addHeader("updateTime", "更新时间")
        .addHeader("updateBy", "更新人id").addHeader("traceId", "调用链");
  }

  @Override
  public List<WeekInfo> getWeekList(Long factoryId, String beginDate, String endDate) {
    List<Calendar> calendarList = this.list(new LambdaQueryWrapper<Calendar>().eq(Calendar::getFactoryId, factoryId).eq(Calendar::getCalendarDisabled, 0));
    $.requireNonNullCanIgnoreException(CollUtil.isNotEmpty(calendarList), "未找到日历信息");
    List<WeekInfo> weekList = DateUtils.getWeekList(beginDate, endDate);
    Map<String, List<WeekInfo>> yMap = weekList.stream().collect(Collectors.groupingBy(t -> t.getCurrentDay().substring(0, 4)));
    yMap.forEach((ys, mlist) -> {
      Map<Integer, List<WeekInfo>> mListMap = mlist.stream().collect(Collectors.groupingBy(c -> Integer.parseInt(c.getCurrentDay().substring(5, 7))));
      this.calendarDayService.list(new LambdaQueryWrapper<CalendarDay>().in(CalendarDay::getCalendarId, calendarList.stream().map(BaseEntity::getId).toList())
          .eq(CalendarDay::getDayYear, Integer.valueOf(ys)).in(CalendarDay::getDayMonth, mListMap.keySet())).forEach(m -> {
        List<WeekInfo> weekInfos = mListMap.get(m.getDayMonth());
        IntStream.range(1, weekInfos.size()).forEach(i -> {
          ReflectUtil.invoke(weekInfos.get(i), "setIsWorkDay", TRUE.equals(ReflectUtil.getFieldValue(m, getField(m, "day" + i))));
        });
      });
    });
    log.info("getWeekList factoryId:{} {} {} {}", factoryId, beginDate, endDate, JSON.toJSONString(weekList));
    return weekList;
  }
}

