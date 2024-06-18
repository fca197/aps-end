package com.olivia.peanut.aps.api.entity.apsSchedulingConstraints;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsSchedulingConstraints)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-04-13 21:32:13
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingConstraintsDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

