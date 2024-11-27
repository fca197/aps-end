package com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersionDetailMachine;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 排程版本详情_机器(ApsSchedulingDayConfigVersionDetailMachine)根据ID删除多个反参
 *
 * @author makejava
 * @since 2024-10-27 00:12:54
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingDayConfigVersionDetailMachineDeleteByIdListRes {
  /***
   * 受影响行数
   */
  private int count;

}

