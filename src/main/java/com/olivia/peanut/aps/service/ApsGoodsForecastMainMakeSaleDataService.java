package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastMainMakeSaleData.*;
import com.olivia.peanut.aps.model.ApsGoodsForecastMainMakeSaleData;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * (ApsGoodsForecastMainMakeSaleData)表服务接口
 *
 * @author peanut
 * @since 2024-04-08 09:52:51
 */
public interface ApsGoodsForecastMainMakeSaleDataService extends MPJBaseService<ApsGoodsForecastMainMakeSaleData> {

  ApsGoodsForecastMainMakeSaleDataQueryListRes queryList(ApsGoodsForecastMainMakeSaleDataQueryListReq req);

  DynamicsPage<ApsGoodsForecastMainMakeSaleDataExportQueryPageListInfoRes> queryPageList(ApsGoodsForecastMainMakeSaleDataExportQueryPageListReq req);


  void setName(List<? extends ApsGoodsForecastMainMakeSaleDataDto> apsGoodsForecastMainMakeSaleDataDtoList);
}

