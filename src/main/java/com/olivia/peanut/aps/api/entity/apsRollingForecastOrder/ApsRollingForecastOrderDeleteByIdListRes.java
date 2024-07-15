package com.olivia.peanut.aps.api.entity.apsRollingForecastOrder;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 滚动预测(ApsRollingForecastOrder)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-07-14 20:22:28
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsRollingForecastOrderDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

