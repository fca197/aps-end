package com.olivia.peanut.aps.service;

import com.olivia.sdk.utils.DynamicsPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.olivia.peanut.aps.model.ApsSchedulingDayConfig;

import java.util.List;

import com.github.yulichang.base.MPJBaseService;

import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfig.*;

/**
 * 排程版本表(ApsSchedulingDayConfig)表服务接口
 *
 * @author peanut
 * @since 2024-07-19 19:19:49
 */
public interface ApsSchedulingDayConfigService extends MPJBaseService<ApsSchedulingDayConfig> {

  ApsSchedulingDayConfigQueryListRes queryList(ApsSchedulingDayConfigQueryListReq req);

  DynamicsPage<ApsSchedulingDayConfigExportQueryPageListInfoRes> queryPageList(ApsSchedulingDayConfigExportQueryPageListReq req);


  void setName(List<? extends ApsSchedulingDayConfigDto> apsSchedulingDayConfigDtoList);

  ApsSchedulingDayConfigInsertRes save(ApsSchedulingDayConfigInsertReq req);

  ApsSchedulingDayConfigUpdateByIdRes updateById(ApsSchedulingDayConfigUpdateByIdReq req);
}

