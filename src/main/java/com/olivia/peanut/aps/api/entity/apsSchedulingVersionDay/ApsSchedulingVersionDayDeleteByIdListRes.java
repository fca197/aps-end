package com.olivia.peanut.aps.api.entity.apsSchedulingVersionDay;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsSchedulingVersionDay)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-04-23 14:37:05
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingVersionDayDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

