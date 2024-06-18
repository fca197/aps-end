package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastMakeSaleData.*;
import com.olivia.peanut.aps.model.ApsGoodsForecastMakeSaleData;
import com.olivia.sdk.utils.DynamicsPage;
import java.util.List;

/**
 * (ApsGoodsForecastMakeSaleData)表服务接口
 *
 * @author peanut
 * @since 2024-04-07 15:07:49
 */
public interface ApsGoodsForecastMakeSaleDataService extends MPJBaseService<ApsGoodsForecastMakeSaleData> {

  ApsGoodsForecastMakeSaleDataQueryListRes queryList(ApsGoodsForecastMakeSaleDataQueryListReq req);

  DynamicsPage<ApsGoodsForecastMakeSaleDataExportQueryPageListInfoRes> queryPageList(ApsGoodsForecastMakeSaleDataExportQueryPageListReq req);


  void setName(List<? extends ApsGoodsForecastMakeSaleDataDto> apsGoodsForecastMakeSaleDataDtoList);
}

