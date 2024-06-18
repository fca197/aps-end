package com.olivia.peanut.aps.api.entity.apsSchedulingVersionCapacity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsSchedulingVersionCapacity)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-04-18 14:57:34
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingVersionCapacityDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

