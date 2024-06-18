package com.olivia.peanut.aps.api.entity.apsGoodsForecastMakeProjectData;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecastMakeProjectData)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-05-10 13:58:07
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastMakeProjectDataDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

