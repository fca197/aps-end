package com.olivia.peanut.aps.api.entity.apsSchedulingDayConfig;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 排程版本表(ApsSchedulingDayConfig)保存返回
 *
 * @author peanut
 * @since 2024-07-19 19:19:49
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingDayConfigInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

