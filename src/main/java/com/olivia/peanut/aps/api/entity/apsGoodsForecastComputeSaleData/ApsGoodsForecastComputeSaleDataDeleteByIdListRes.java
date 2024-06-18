package com.olivia.peanut.aps.api.entity.apsGoodsForecastComputeSaleData;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecastComputeSaleData)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-03-31 20:58:34
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastComputeSaleDataDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

