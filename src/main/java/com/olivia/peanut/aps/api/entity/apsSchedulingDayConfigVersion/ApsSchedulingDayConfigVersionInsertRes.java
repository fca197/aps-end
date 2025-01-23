package com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersion;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 排程版本(ApsSchedulingDayConfigVersion)保存返回
 *
 * @author peanut
 * @since 2024-07-19 19:19:54
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingDayConfigVersionInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

