package com.olivia.peanut.aps.api.entity.apsRollingForecastFactoryCapacity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 滚动预测(ApsRollingForecastFactoryCapacity)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-07-14 20:22:22
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsRollingForecastFactoryCapacityDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

