package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersionDetailMachine.*;
import com.olivia.peanut.aps.model.ApsSchedulingDayConfigVersionDetailMachine;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * 排程版本详情_机器(ApsSchedulingDayConfigVersionDetailMachine)表服务接口
 *
 * @author makejava
 * @since 2024-10-27 00:12:55
 */
public interface ApsSchedulingDayConfigVersionDetailMachineService extends MPJBaseService<ApsSchedulingDayConfigVersionDetailMachine> {
  ApsSchedulingDayConfigVersionDetailMachineQueryListRes queryList(ApsSchedulingDayConfigVersionDetailMachineQueryListReq req);

  DynamicsPage<ApsSchedulingDayConfigVersionDetailMachineExportQueryPageListInfoRes> queryPageList(ApsSchedulingDayConfigVersionDetailMachineExportQueryPageListReq req);


  void setName(List<? extends ApsSchedulingDayConfigVersionDetailMachineDto> apsSchedulingDayConfigVersionDetailMachineDtoList);
}

