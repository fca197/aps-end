package com.olivia.peanut.aps.api.entity.apsRollingForecastFactoryCapacity;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.time.LocalDate;
import java.time.LocalDateTime;

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
public class ApsRollingForecastFactoryCapacityQueryByIdListReq {

  private List<Long> idList;


  public void checkParam() {
  }

}

