package com.olivia.peanut.aps.api.entity.apsGoodsForecastMainMake;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecastMainMake)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-04-08 09:52:49
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastMainMakeDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

