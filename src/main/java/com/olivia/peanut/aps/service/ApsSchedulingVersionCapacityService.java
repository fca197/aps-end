package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsSchedulingVersionCapacity.*;
import com.olivia.peanut.aps.model.ApsSchedulingVersionCapacity;
import com.olivia.sdk.utils.DynamicsPage;
import java.util.List;

/**
 * (ApsSchedulingVersionCapacity)表服务接口
 *
 * @author peanut
 * @since 2024-04-18 14:57:34
 */
public interface ApsSchedulingVersionCapacityService extends MPJBaseService<ApsSchedulingVersionCapacity> {

  ApsSchedulingVersionCapacityQueryListRes queryList(ApsSchedulingVersionCapacityQueryListReq req);

  DynamicsPage<ApsSchedulingVersionCapacityExportQueryPageListInfoRes> queryPageList(ApsSchedulingVersionCapacityExportQueryPageListReq req);


  void setName(List<? extends ApsSchedulingVersionCapacityDto> apsSchedulingVersionCapacityDtoList);

}

