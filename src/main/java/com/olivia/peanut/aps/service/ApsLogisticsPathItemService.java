package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsLogisticsPathItem.*;
import com.olivia.peanut.aps.model.ApsLogisticsPathItem;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * 物流路详情径表(ApsLogisticsPathItem)表服务接口
 *
 * @author peanut
 * @since 2024-07-18 13:27:40
 */
public interface ApsLogisticsPathItemService extends MPJBaseService<ApsLogisticsPathItem> {

  ApsLogisticsPathItemQueryListRes queryList(ApsLogisticsPathItemQueryListReq req);

  DynamicsPage<ApsLogisticsPathItemExportQueryPageListInfoRes> queryPageList(ApsLogisticsPathItemExportQueryPageListReq req);


  void setName(List<? extends ApsLogisticsPathItemDto> apsLogisticsPathItemDtoList);
}

