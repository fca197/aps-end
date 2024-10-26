package com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersionDetailMachine;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 排程版本详情_机器(ApsSchedulingDayConfigVersionDetailMachine)查询对象入参
 *
 * @author makejava
 * @since 2024-10-27 00:12:55
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingDayConfigVersionDetailMachineQueryByIdListReq {
  private List<Long> idList;


  public void checkParam() {
  }

}

