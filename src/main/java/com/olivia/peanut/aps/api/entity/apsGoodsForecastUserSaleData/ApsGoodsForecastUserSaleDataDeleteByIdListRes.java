package com.olivia.peanut.aps.api.entity.apsGoodsForecastUserSaleData;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecastUserSaleData)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-03-30 18:29:07
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastUserSaleDataDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

