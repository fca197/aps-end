package com.olivia.peanut.base.api.entity.shift;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (Shift)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-04-04 12:10:15
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ShiftDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

