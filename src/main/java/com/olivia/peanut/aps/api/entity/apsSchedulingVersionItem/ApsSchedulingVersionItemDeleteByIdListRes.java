package com.olivia.peanut.aps.api.entity.apsSchedulingVersionItem;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsSchedulingVersionItem)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-04-16 09:24:05
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingVersionItemDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

