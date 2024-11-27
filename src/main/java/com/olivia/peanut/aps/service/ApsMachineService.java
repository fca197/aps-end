package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsMachine.*;
import com.olivia.peanut.aps.model.ApsMachine;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * aps 生产机器(ApsMachine)表服务接口
 *
 * @author makejava
 * @since 2024-10-24 16:31:15
 */
public interface ApsMachineService extends MPJBaseService<ApsMachine> {
  ApsMachineQueryListRes queryList(ApsMachineQueryListReq req);

  DynamicsPage<ApsMachineExportQueryPageListInfoRes> queryPageList(ApsMachineExportQueryPageListReq req);


  void setName(List<? extends ApsMachineDto> apsMachineDtoList);
}

