package com.olivia.peanut.aps.service;

import com.olivia.peanut.aps.api.entity.apsLogisticsPathItem.*;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.peanut.aps.model.ApsLogisticsPathItem;
import java.util.List;
import com.github.yulichang.base.MPJBaseService;

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

