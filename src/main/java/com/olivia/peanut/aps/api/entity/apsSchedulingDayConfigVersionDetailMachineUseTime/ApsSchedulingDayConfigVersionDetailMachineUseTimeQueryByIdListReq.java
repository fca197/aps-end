package com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersionDetailMachineUseTime;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 排程结果机器使用率(ApsSchedulingDayConfigVersionDetailMachineUseTime)查询对象入参
 *
 * @author makejava
 * @since 2024-11-11 15:21:50
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingDayConfigVersionDetailMachineUseTimeQueryByIdListReq {
  private List<Long> idList;


}

