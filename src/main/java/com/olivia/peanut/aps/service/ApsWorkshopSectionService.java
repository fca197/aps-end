package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.workshopSection.WorkshopSectionExportQueryPageListInfoRes;
import com.olivia.peanut.aps.api.entity.workshopSection.WorkshopSectionExportQueryPageListReq;
import com.olivia.peanut.aps.api.entity.workshopSection.WorkshopSectionQueryListReq;
import com.olivia.peanut.aps.api.entity.workshopSection.WorkshopSectionQueryListRes;
import com.olivia.peanut.aps.model.ApsWorkshopSection;
import com.olivia.sdk.utils.DynamicsPage;

/**
 * 工段信息(WorkshopSection)表服务接口
 *
 * @author makejava
 * @since 2024-03-11 10:55:23
 */
public interface ApsWorkshopSectionService extends MPJBaseService<ApsWorkshopSection> {

  WorkshopSectionQueryListRes queryList(WorkshopSectionQueryListReq req);

  DynamicsPage<WorkshopSectionExportQueryPageListInfoRes> queryPageList(WorkshopSectionExportQueryPageListReq req);

}

