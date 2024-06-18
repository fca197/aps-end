package com.olivia.peanut.aps.api.entity.apsGoodsForecastMakeBomUse;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecastMakeBomUse)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-05-15 10:26:03
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastMakeBomUseDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

