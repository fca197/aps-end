package com.olivia.peanut.aps.api.entity.apsRollingForecastFactoryCapacity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 滚动预测(ApsRollingForecastFactoryCapacity)查询对象入参
 *
 * @author peanut
 * @since 2024-07-14 20:22:23
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsRollingForecastFactoryCapacityExportQueryPageListReq {

  private int pageNum;
  private int pageSize;
  private Boolean queryPage = true;
  private ApsRollingForecastFactoryCapacityDto data;


  public void checkParam() {
  }

}

