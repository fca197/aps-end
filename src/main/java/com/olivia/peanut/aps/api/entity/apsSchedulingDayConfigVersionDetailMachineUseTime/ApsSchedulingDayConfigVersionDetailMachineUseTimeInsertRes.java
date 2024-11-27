package com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersionDetailMachineUseTime;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 排程结果机器使用率(ApsSchedulingDayConfigVersionDetailMachineUseTime)保存返回
 *
 * @author makejava
 * @since 2024-11-11 15:21:48
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingDayConfigVersionDetailMachineUseTimeInsertRes {
  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

