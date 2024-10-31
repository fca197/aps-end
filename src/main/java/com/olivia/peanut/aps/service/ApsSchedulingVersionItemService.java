package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsSchedulingVersionItem.*;
import com.olivia.peanut.aps.model.ApsSchedulingVersionItem;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * (ApsSchedulingVersionItem)表服务接口
 *
 * @author peanut
 * @since 2024-04-16 09:24:06
 */
public interface ApsSchedulingVersionItemService extends MPJBaseService<ApsSchedulingVersionItem> {

  ApsSchedulingVersionItemQueryListRes queryList(ApsSchedulingVersionItemQueryListReq req);

  DynamicsPage<ApsSchedulingVersionItemExportQueryPageListInfoRes> queryPageList(ApsSchedulingVersionItemExportQueryPageListReq req);


  void setName(List<? extends ApsSchedulingVersionItemDto> apsSchedulingVersionItemDtoList);
}

