package com.olivia.peanut.base.api.poi;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.olivia.peanut.base.model.Calendar;
import com.olivia.peanut.base.service.CalendarService;
import com.olivia.sdk.listener.BaseExportDataPoiListener;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class CalendarExportDataPoiListener implements BaseExportDataPoiListener<Calendar> {

  @Resource
  CalendarService calendarService;

  @Override
  public String getFileName() {
    return "日历";
  }

  @Override
  public List<Calendar> getExportData(Wrapper<Calendar> query) {
    return calendarService.list(query);
  }
}
