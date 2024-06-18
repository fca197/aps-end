package com.olivia.peanut.aps.api.entity.apsSchedulingVersion;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsSchedulingVersion)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-04-13 21:32:14
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingVersionDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

