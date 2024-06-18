package com.olivia.peanut.aps.api.entity.apsMakeCapacitySaleConfig;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsMakeCapacitySaleConfig)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-04-13 12:05:06
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsMakeCapacitySaleConfigDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

