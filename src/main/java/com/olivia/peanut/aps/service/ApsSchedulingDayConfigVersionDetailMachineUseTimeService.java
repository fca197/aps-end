package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersionDetailMachineUseTime.*;
import com.olivia.peanut.aps.model.ApsSchedulingDayConfigVersionDetailMachineUseTime;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * 排程结果机器使用率(ApsSchedulingDayConfigVersionDetailMachineUseTime)表服务接口
 *
 * @author makejava
 * @since 2024-11-11 15:21:49
 */
public interface ApsSchedulingDayConfigVersionDetailMachineUseTimeService extends MPJBaseService<ApsSchedulingDayConfigVersionDetailMachineUseTime> {
  ApsSchedulingDayConfigVersionDetailMachineUseTimeQueryListRes queryList(ApsSchedulingDayConfigVersionDetailMachineUseTimeQueryListReq req);

  DynamicsPage<ApsSchedulingDayConfigVersionDetailMachineUseTimeExportQueryPageListInfoRes> queryPageList(ApsSchedulingDayConfigVersionDetailMachineUseTimeExportQueryPageListReq req);


  void setName(List<? extends ApsSchedulingDayConfigVersionDetailMachineUseTimeDto> apsSchedulingDayConfigVersionDetailMachineUseTimeDtoList);
}

