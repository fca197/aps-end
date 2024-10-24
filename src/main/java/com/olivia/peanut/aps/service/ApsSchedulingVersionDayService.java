package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsSchedulingVersionDay.*;
import com.olivia.peanut.aps.model.ApsSchedulingVersionDay;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * (ApsSchedulingVersionDay)表服务接口
 *
 * @author peanut
 * @since 2024-04-23 14:37:05
 */
public interface ApsSchedulingVersionDayService extends MPJBaseService<ApsSchedulingVersionDay> {

  ApsSchedulingVersionDayQueryListRes queryList(ApsSchedulingVersionDayQueryListReq req);

  DynamicsPage<ApsSchedulingVersionDayExportQueryPageListInfoRes> queryPageList(ApsSchedulingVersionDayExportQueryPageListReq req);


  void setName(List<? extends ApsSchedulingVersionDayDto> apsSchedulingVersionDayDtoList);
}

