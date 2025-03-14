package com.olivia.peanut.base.api.entity.calendar;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class CalendarDayByIdReq {

  @NotNull(message = "请选择日历")
  private Long id;
  @NotNull(message = "工作年份不能为空")
  private Integer dayYear;
  @NotNull(message = "月份不能为空")
  private Integer dayMonth;
}
