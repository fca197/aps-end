package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersion.*;
import com.olivia.peanut.aps.model.ApsSchedulingDayConfigVersion;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * 排程版本(ApsSchedulingDayConfigVersion)表服务接口
 *
 * @author peanut
 * @since 2024-07-19 19:19:55
 */
public interface ApsSchedulingDayConfigVersionService extends MPJBaseService<ApsSchedulingDayConfigVersion> {

  ApsSchedulingDayConfigVersionInsertRes save(ApsSchedulingDayConfigVersionInsertReq req);

  ApsSchedulingDayConfigVersionQueryListRes queryList(ApsSchedulingDayConfigVersionQueryListReq req);

  DynamicsPage<ApsSchedulingDayConfigVersionExportQueryPageListInfoRes> queryPageList(ApsSchedulingDayConfigVersionExportQueryPageListReq req);


  void setName(List<? extends ApsSchedulingDayConfigVersionDto> apsSchedulingDayConfigVersionDtoList);

  ApsSchedulingDayConfigVersionDetailListRes detailList(ApsSchedulingDayConfigVersionDetailListReq req);

  ApsSchedulingDayConfigVersionUpdateOrderSortIndexRes updateOrderSortIndex(ApsSchedulingDayConfigVersionUpdateOrderSortIndexReq req);

}

