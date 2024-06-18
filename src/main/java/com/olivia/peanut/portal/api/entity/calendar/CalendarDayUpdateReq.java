package com.olivia.peanut.portal.api.entity.calendar;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

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
  private Integer workYear;
  @NotNull(message = "工作日不能为空")
  private List<Integer> defaultWorkDayList;
  private List<List<String>> workDayList;
  private List<List<String>> noWorkDayList;
}
