package com.olivia.peanut.aps.service;

import com.olivia.sdk.utils.DynamicsPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.olivia.peanut.aps.model.ApsMachine;

import java.util.List;

import com.github.yulichang.base.MPJBaseService;

import com.olivia.peanut.aps.api.entity.apsMachine.*;

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

