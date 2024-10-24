package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastMainGoodsData.*;
import com.olivia.peanut.aps.model.ApsGoodsForecastMainGoodsData;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * (ApsGoodsForecastMainGoodsData)表服务接口
 *
 * @author peanut
 * @since 2024-04-02 13:44:29
 */
public interface ApsGoodsForecastMainGoodsDataService extends MPJBaseService<ApsGoodsForecastMainGoodsData> {

  ApsGoodsForecastMainGoodsDataQueryListRes queryList(ApsGoodsForecastMainGoodsDataQueryListReq req);

  DynamicsPage<ApsGoodsForecastMainGoodsDataExportQueryPageListInfoRes> queryPageList(ApsGoodsForecastMainGoodsDataExportQueryPageListReq req);


  void setName(List<? extends ApsGoodsForecastMainGoodsDataDto> apsGoodsForecastMainGoodsDataDtoList);
}

