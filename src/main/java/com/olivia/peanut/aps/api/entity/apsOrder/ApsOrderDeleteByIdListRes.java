package com.olivia.peanut.aps.api.entity.apsOrder;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsOrder)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-04-09 13:19:35
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

