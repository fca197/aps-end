package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.workshopStation.WorkshopStationExportQueryPageListInfoRes;
import com.olivia.peanut.aps.api.entity.workshopStation.WorkshopStationExportQueryPageListReq;
import com.olivia.peanut.aps.api.entity.workshopStation.WorkshopStationQueryListReq;
import com.olivia.peanut.aps.api.entity.workshopStation.WorkshopStationQueryListRes;
import com.olivia.peanut.aps.model.ApsWorkshopStation;
import com.olivia.sdk.utils.DynamicsPage;

/**
 * 工位信息(WorkshopStation)表服务接口
 *
 * @author makejava
 * @since 2024-03-11 10:55:23
 */
public interface ApsWorkshopStationService extends MPJBaseService<ApsWorkshopStation> {

  WorkshopStationQueryListRes queryList(WorkshopStationQueryListReq req);

  DynamicsPage<WorkshopStationExportQueryPageListInfoRes> queryPageList(WorkshopStationExportQueryPageListReq req);

}

