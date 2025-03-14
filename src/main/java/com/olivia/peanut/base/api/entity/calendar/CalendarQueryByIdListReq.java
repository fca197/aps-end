package com.olivia.peanut.base.api.entity.calendar;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 工作日历(Calendar)查询对象入参
 *
 * @author makejava
 * @since 2024-03-11 10:44:03
 */
@Accessors(chain = true)
@Getter
@Setter

public class CalendarQueryByIdListReq {

  private List<Long> idList;


}

