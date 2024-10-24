package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastUserSaleData.*;
import com.olivia.peanut.aps.model.ApsGoodsForecastUserSaleData;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * (ApsGoodsForecastUserSaleData)表服务接口
 *
 * @author peanut
 * @since 2024-03-30 18:29:07
 */
public interface ApsGoodsForecastUserSaleDataService extends MPJBaseService<ApsGoodsForecastUserSaleData> {

  ApsGoodsForecastUserSaleDataQueryListRes queryList(ApsGoodsForecastUserSaleDataQueryListReq req);

  DynamicsPage<ApsGoodsForecastUserSaleDataExportQueryPageListInfoRes> queryPageList(ApsGoodsForecastUserSaleDataExportQueryPageListReq req);


  void setName(List<? extends ApsGoodsForecastUserSaleDataDto> apsGoodsForecastUserSaleDataDtoList);
}

