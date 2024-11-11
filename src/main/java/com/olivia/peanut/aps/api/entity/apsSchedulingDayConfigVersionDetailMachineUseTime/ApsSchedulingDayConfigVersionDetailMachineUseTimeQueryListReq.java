package com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersionDetailMachineUseTime;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 排程结果机器使用率(ApsSchedulingDayConfigVersionDetailMachineUseTime)查询对象入参
 *
 * @author makejava
 * @since 2024-11-11 15:21:48
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingDayConfigVersionDetailMachineUseTimeQueryListReq {

  private ApsSchedulingDayConfigVersionDetailMachineUseTimeDto data;
}

