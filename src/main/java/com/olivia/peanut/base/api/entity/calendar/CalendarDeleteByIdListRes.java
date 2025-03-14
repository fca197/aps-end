package com.olivia.peanut.base.api.entity.calendar;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 工作日历(Calendar)根据ID删除多个反参
 *
 * @author makejava
 * @since 2024-03-11 10:44:03
 */
@Accessors(chain = true)
@Getter
@Setter

public class CalendarDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

