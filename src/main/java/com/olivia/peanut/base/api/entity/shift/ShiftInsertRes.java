package com.olivia.peanut.base.api.entity.shift;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (Shift)保存返回
 *
 * @author peanut
 * @since 2024-04-04 12:10:15
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ShiftInsertRes {

  /****
   * 写入行数
   */
  private int count;
  private Long id;

}

