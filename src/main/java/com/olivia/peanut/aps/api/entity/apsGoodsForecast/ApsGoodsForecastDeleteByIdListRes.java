package com.olivia.peanut.aps.api.entity.apsGoodsForecast;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecast)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-03-30 13:38:53
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

