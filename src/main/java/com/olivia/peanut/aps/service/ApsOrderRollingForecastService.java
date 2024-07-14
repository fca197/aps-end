package com.olivia.peanut.aps.service;

import com.olivia.sdk.utils.DynamicsPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.olivia.peanut.aps.model.ApsOrderRollingForecast;
import java.util.List;
import com.github.yulichang.base.MPJBaseService;

import com.olivia.peanut.aps.api.entity.apsOrderRollingForecast.*;

/**
 * 滚动预测(ApsOrderRollingForecast)表服务接口
 *
 * @author peanut
 * @since 2024-07-14 19:43:51
 */
public interface ApsOrderRollingForecastService extends MPJBaseService<ApsOrderRollingForecast> {

  ApsOrderRollingForecastQueryListRes queryList(ApsOrderRollingForecastQueryListReq req);

  DynamicsPage<ApsOrderRollingForecastExportQueryPageListInfoRes> queryPageList(ApsOrderRollingForecastExportQueryPageListReq req);


  void setName(List<? extends ApsOrderRollingForecastDto> apsOrderRollingForecastDtoList);
}

