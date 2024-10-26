package com.olivia.peanut.aps.service;

import com.olivia.sdk.utils.DynamicsPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.olivia.peanut.aps.model.ApsSchedulingDayConfigVersionDetailMachine;

import java.util.List;

import com.github.yulichang.base.MPJBaseService;

import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersionDetailMachine.*;

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

