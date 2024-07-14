package com.olivia.peanut.aps.service;

import com.olivia.sdk.utils.DynamicsPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.olivia.peanut.aps.model.ApsRollingForecastOrder;
import java.util.List;
import com.github.yulichang.base.MPJBaseService;

import com.olivia.peanut.aps.api.entity.apsRollingForecastOrder.*;

/**
 * 滚动预测(ApsRollingForecastOrder)表服务接口
 *
 * @author peanut
 * @since 2024-07-14 20:22:28
 */
public interface ApsRollingForecastOrderService extends MPJBaseService<ApsRollingForecastOrder> {

  ApsRollingForecastOrderQueryListRes queryList(ApsRollingForecastOrderQueryListReq req);

  DynamicsPage<ApsRollingForecastOrderExportQueryPageListInfoRes> queryPageList(ApsRollingForecastOrderExportQueryPageListReq req);


  void setName(List<? extends ApsRollingForecastOrderDto> apsRollingForecastOrderDtoList);
}

