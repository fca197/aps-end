package com.olivia.peanut.aps.api.entity.apsGoodsForecastMakeSaleData;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecastMakeSaleData)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-04-07 15:07:49
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastMakeSaleDataDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

