package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsOrderGoodsForecastMake.*;
import com.olivia.peanut.aps.model.ApsOrderGoodsForecastMake;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * 订单商品节点预测表(ApsOrderGoodsForecastMake)表服务接口
 *
 * @author peanut
 * @since 2024-06-02 23:11:41
 */
public interface ApsOrderGoodsForecastMakeService extends MPJBaseService<ApsOrderGoodsForecastMake> {

  ApsOrderGoodsForecastMakeQueryListRes queryList(ApsOrderGoodsForecastMakeQueryListReq req);

  DynamicsPage<ApsOrderGoodsForecastMakeExportQueryPageListInfoRes> queryPageList(ApsOrderGoodsForecastMakeExportQueryPageListReq req);


  void setName(List<? extends ApsOrderGoodsForecastMakeDto> apsOrderGoodsForecastMakeDtoList);
}

