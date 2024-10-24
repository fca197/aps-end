package com.olivia.peanut.portal.api.impl;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.portal.api.CalendarApi;
import com.olivia.peanut.portal.api.entity.calendar.*;
import com.olivia.peanut.portal.api.impl.listener.CalendarImportListener;
import com.olivia.peanut.portal.model.Calendar;
import com.olivia.peanut.portal.model.CalendarDay;
import com.olivia.peanut.portal.service.CalendarDayService;
import com.olivia.peanut.portal.service.CalendarService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import jakarta.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 工作日历(Calendar)表服务实现类
 *
 * @author makejava
 * @since 2024-03-11 10:44:03
 */
@RestController
public class CalendarApiImpl implements CalendarApi {

  @Resource
  CalendarDayService calendarDayService;
  private @Autowired CalendarService calendarService;

  /****
   * insert
   *
   */
  public @Override CalendarInsertRes insert(CalendarInsertReq req) {
    this.calendarService.save($.copy(req, Calendar.class));
    return new CalendarInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override CalendarDeleteByIdListRes deleteByIdList(CalendarDeleteByIdListReq req) {
    calendarService.removeByIds(req.getIdList());
    return new CalendarDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override CalendarQueryListRes queryList(CalendarQueryListReq req) {
    return calendarService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override CalendarUpdateByIdRes updateById(CalendarUpdateByIdReq req) {
    calendarService.updateById($.copy(req, Calendar.class));
    return new CalendarUpdateByIdRes();

  }

  public @Override DynamicsPage<CalendarExportQueryPageListInfoRes> queryPageList(CalendarExportQueryPageListReq req) {
    return calendarService.queryPageList(req);
  }

  public @Override void queryPageListExport(CalendarExportQueryPageListReq req) {
    DynamicsPage<CalendarExportQueryPageListInfoRes> page = queryPageList(req);
    List<CalendarExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<CalendarExportQueryPageListInfoRes> listInfoRes = $.copyList(list, CalendarExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(CalendarExportQueryPageListInfoRes.class, listInfoRes, "工作日历");
  }

  public @Override CalendarImportRes importData(@RequestParam("file") MultipartFile file) {
    List<CalendarImportReq> reqList = PoiExcelUtil.readData(file, new CalendarImportListener(), CalendarImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<Calendar> readList = $.copyList(reqList, Calendar.class);
    boolean bool = calendarService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new CalendarImportRes().setCount(c);
  }

  public @Override CalendarQueryByIdListRes queryByIdListRes(CalendarQueryByIdListReq req) {
    MPJLambdaWrapper<Calendar> q = new MPJLambdaWrapper<Calendar>(Calendar.class).selectAll(Calendar.class).in(Calendar::getId, req.getIdList());
    List<Calendar> list = this.calendarService.list(q);
    List<CalendarQueryByIdListRes.Info> dataList = $.copyList(list, CalendarQueryByIdListRes.Info.class);
    return new CalendarQueryByIdListRes().setDataList(dataList);
  }

  @Override
  @Transactional
  public CalendarDayUpdateRes calendarDayUpdate(CalendarDayUpdateReq req) {
    Calendar calendar = this.calendarService.getById(req.getId());
    $.requireNonNullCanIgnoreException(calendar, "日历不存在");
    calendarDayService.remove(new LambdaUpdateWrapper<CalendarDay>().eq(CalendarDay::getCalendarId, req.getId()));
    List<CalendarDay> calendarDayList = new ArrayList<>();

    List<Integer> workYearList = req.getWorkYear();
    workYearList.forEach(workYear -> {
      IntStream.range(1, 13).forEach(t -> {
        CalendarDay day = new CalendarDay();
        day.setCalendarId(req.getId()).setFactoryId(calendar.getFactoryId());

        day.setDayMonth(t).setDayYear(workYear);
        IntStream.range(1, 32).forEach(d -> {
          String cuDay = workYear + "-" + StringUtils.right("0" + t, 2) + "-" + StringUtils.right("0" + d, 2);
          int week = DateUtil.parseDate(cuDay).dayOfWeekEnum().getIso8601Value();
          boolean b = FALSE;
          // 工作日
          if (req.getDefaultWorkDayList().contains(week)) {
            b = TRUE;
          }
          Boolean inSide = isInSide(req.getWorkDayList(), cuDay);
          if (Objects.nonNull(inSide)) {
            b = inSide;
          }
          inSide = isInSide(req.getNoWorkDayList(), cuDay);
          if (Objects.nonNull(inSide)) {
            b = !inSide;
          }
          ReflectUtil.setFieldValue(day, "day" + d, b);
        });
        calendarDayList.add(day);
      });
    });
    this.calendarDayService.saveBatch(calendarDayList);
    return new CalendarDayUpdateRes();
  }

  private Boolean isInSide(List<List<String>> dayList, String day) {
    if (CollUtil.isNotEmpty(dayList)) {
      for (List<String> days : dayList) {
        if (Objects.nonNull(days) && days.size() == 2 && day.compareTo(days.get(0)) >= 0 && day.compareTo(days.get(1)) <= 0) {
          return true;
        }
      }
    }
    return null;
  }

  @Override
  public CalendarDayByIdRes calendarDayById(CalendarDayByIdReq req) {
    Calendar calendar = this.calendarService.getById(req.getId());
    $.requireNonNullCanIgnoreException(calendar, "日历不存在");
    CalendarDay one = this.calendarDayService.getOne(Wrappers.<CalendarDay>lambdaQuery().eq(CalendarDay::getCalendarId, req.getId()).eq(CalendarDay::getDayYear, req.getDayYear())
        .eq(CalendarDay::getDayMonth, req.getDayMonth()), false);
    $.requireNonNullCanIgnoreException(one, "当前日期没有设置工作日");
    return $.copy(one, CalendarDayByIdRes.class);
  }
}
