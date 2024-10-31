package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastMakeProjectData.*;
import com.olivia.peanut.aps.model.ApsGoodsForecastMakeProjectData;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * (ApsGoodsForecastMakeProjectData)表服务接口
 *
 * @author peanut
 * @since 2024-05-10 13:58:08
 */
public interface ApsGoodsForecastMakeProjectDataService extends MPJBaseService<ApsGoodsForecastMakeProjectData> {

  ApsGoodsForecastMakeProjectDataQueryListRes queryList(ApsGoodsForecastMakeProjectDataQueryListReq req);

  DynamicsPage<ApsGoodsForecastMakeProjectDataExportQueryPageListInfoRes> queryPageList(ApsGoodsForecastMakeProjectDataExportQueryPageListReq req);


  void setName(List<? extends ApsGoodsForecastMakeProjectDataDto> apsGoodsForecastMakeProjectDataDtoList);
}

