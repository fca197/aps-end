package com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersionDetailMachine;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 排程版本详情_机器(ApsSchedulingDayConfigVersionDetailMachine)保存返回
 *
 * @author makejava
 * @since 2024-10-27 00:12:54
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingDayConfigVersionDetailMachineImportRes {
  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

