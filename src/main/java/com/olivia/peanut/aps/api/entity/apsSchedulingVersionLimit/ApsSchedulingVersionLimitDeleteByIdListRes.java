package com.olivia.peanut.aps.api.entity.apsSchedulingVersionLimit;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsSchedulingVersionLimit)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-04-19 14:56:59
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingVersionLimitDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

