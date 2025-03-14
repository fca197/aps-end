package com.olivia.peanut.base.api.entity.calendar;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class CalendarDayUpdateReq {

  @NotNull(message = "请选择日历")
  private Long id;
  @NotNull(message = "工作年份不能为空")
  @Size(min = 1, max = 10, message = "年限范围为当前{max}年内")
  private List<Integer> workYear;
  @NotNull(message = "工作日不能为空")
  private List<Integer> defaultWorkDayList;
  private List<List<String>> workDayList;
  private List<List<String>> noWorkDayList;
}
