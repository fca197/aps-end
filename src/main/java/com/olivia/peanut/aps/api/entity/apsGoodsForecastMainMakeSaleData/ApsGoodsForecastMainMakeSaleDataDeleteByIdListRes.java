package com.olivia.peanut.aps.api.entity.apsGoodsForecastMainMakeSaleData;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecastMainMakeSaleData)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-04-08 09:52:50
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastMainMakeSaleDataDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

