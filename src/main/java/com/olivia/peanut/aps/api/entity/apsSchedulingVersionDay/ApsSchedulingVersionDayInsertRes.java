package com.olivia.peanut.aps.api.entity.apsSchedulingVersionDay;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsSchedulingVersionDay)保存返回
 *
 * @author peanut
 * @since 2024-04-23 14:37:05
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingVersionDayInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

