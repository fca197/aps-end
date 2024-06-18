package com.olivia.peanut.aps.api.entity.apsGoodsForecastMake;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecastMake)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-04-07 15:07:48
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastMakeDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

