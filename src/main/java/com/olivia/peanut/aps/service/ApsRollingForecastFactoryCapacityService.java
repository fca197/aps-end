package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsRollingForecastFactoryCapacity.*;
import com.olivia.peanut.aps.model.ApsRollingForecastFactoryCapacity;
import com.olivia.peanut.aps.service.pojo.FactoryCapacityDay;
import com.olivia.sdk.utils.DynamicsPage;

import java.time.LocalDate;
import java.util.List;

/**
 * 滚动预测(ApsRollingForecastFactoryCapacity)表服务接口
 *
 * @author peanut
 * @since 2024-07-14 20:22:23
 */
public interface ApsRollingForecastFactoryCapacityService extends MPJBaseService<ApsRollingForecastFactoryCapacity> {

  ApsRollingForecastFactoryCapacityQueryListRes queryList(ApsRollingForecastFactoryCapacityQueryListReq req);

  DynamicsPage<ApsRollingForecastFactoryCapacityExportQueryPageListInfoRes> queryPageList(ApsRollingForecastFactoryCapacityExportQueryPageListReq req);


  void setName(List<? extends ApsRollingForecastFactoryCapacityDto> apsRollingForecastFactoryCapacityDtoList);

  ApsRollingForecastFactoryCapacityInsertRes save(ApsRollingForecastFactoryCapacityInsertReq req);

  List<FactoryCapacityDay> list(Long factoryId, LocalDate beginDate, LocalDate endDate);
}

