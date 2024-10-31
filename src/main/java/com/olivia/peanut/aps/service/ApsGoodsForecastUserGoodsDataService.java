package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastUserGoodsData.*;
import com.olivia.peanut.aps.model.ApsGoodsForecastUserGoodsData;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * (ApsGoodsForecastUserGoodsData)表服务接口
 *
 * @author peanut
 * @since 2024-03-30 18:29:07
 */
public interface ApsGoodsForecastUserGoodsDataService extends MPJBaseService<ApsGoodsForecastUserGoodsData> {

  ApsGoodsForecastUserGoodsDataQueryListRes queryList(ApsGoodsForecastUserGoodsDataQueryListReq req);

  DynamicsPage<ApsGoodsForecastUserGoodsDataExportQueryPageListInfoRes> queryPageList(ApsGoodsForecastUserGoodsDataExportQueryPageListReq req);


  void setName(List<? extends ApsGoodsForecastUserGoodsDataDto> apsGoodsForecastUserGoodsDataDtoList);
}

