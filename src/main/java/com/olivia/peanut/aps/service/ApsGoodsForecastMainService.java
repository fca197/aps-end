package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastMain.*;
import com.olivia.peanut.aps.model.ApsGoodsForecastMain;
import com.olivia.sdk.utils.DynamicsPage;
import java.util.List;

/**
 * (ApsGoodsForecastMain)表服务接口
 *
 * @author peanut
 * @since 2024-04-02 09:42:27
 */
public interface ApsGoodsForecastMainService extends MPJBaseService<ApsGoodsForecastMain> {

  ApsGoodsForecastMainQueryListRes queryList(ApsGoodsForecastMainQueryListReq req);

  DynamicsPage<ApsGoodsForecastMainExportQueryPageListInfoRes> queryPageList(ApsGoodsForecastMainExportQueryPageListReq req);


  void setName(List<? extends ApsGoodsForecastMainDto> apsGoodsForecastMainDtoList);
}

