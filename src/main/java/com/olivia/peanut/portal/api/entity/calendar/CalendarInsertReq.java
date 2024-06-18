package com.olivia.peanut.portal.api.entity.calendar;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 工作日历(Calendar)保存入参
 *
 * @author makejava
 * @since 2024-03-11 10:44:03
 */
@Accessors(chain = true)
@Getter
@Setter

public class CalendarInsertReq extends CalendarDto {

  public void checkParam() {
  }
}

