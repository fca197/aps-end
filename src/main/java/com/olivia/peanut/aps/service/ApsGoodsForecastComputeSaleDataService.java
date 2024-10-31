package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastComputeSaleData.*;
import com.olivia.peanut.aps.model.ApsGoodsForecastComputeSaleData;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * (ApsGoodsForecastComputeSaleData)表服务接口
 *
 * @author peanut
 * @since 2024-03-31 20:58:35
 */
public interface ApsGoodsForecastComputeSaleDataService extends MPJBaseService<ApsGoodsForecastComputeSaleData> {

  ApsGoodsForecastComputeSaleDataQueryListRes queryList(ApsGoodsForecastComputeSaleDataQueryListReq req);

  DynamicsPage<ApsGoodsForecastComputeSaleDataExportQueryPageListInfoRes> queryPageList(ApsGoodsForecastComputeSaleDataExportQueryPageListReq req);


  void setName(List<? extends ApsGoodsForecastComputeSaleDataDto> apsGoodsForecastComputeSaleDataDtoList);
}

