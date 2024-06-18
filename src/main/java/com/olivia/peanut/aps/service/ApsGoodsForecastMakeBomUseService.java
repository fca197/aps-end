package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastMakeBomUse.*;
import com.olivia.peanut.aps.model.ApsGoodsForecastMakeBomUse;
import com.olivia.sdk.utils.DynamicsPage;
import java.util.List;

/**
 * (ApsGoodsForecastMakeBomUse)表服务接口
 *
 * @author peanut
 * @since 2024-05-15 10:26:04
 */
public interface ApsGoodsForecastMakeBomUseService extends MPJBaseService<ApsGoodsForecastMakeBomUse> {

  ApsGoodsForecastMakeBomUseQueryListRes queryList(ApsGoodsForecastMakeBomUseQueryListReq req);

  DynamicsPage<ApsGoodsForecastMakeBomUseExportQueryPageListInfoRes> queryPageList(ApsGoodsForecastMakeBomUseExportQueryPageListReq req);


  void setName(List<? extends ApsGoodsForecastMakeBomUseDto> apsGoodsForecastMakeBomUseDtoList);
}

