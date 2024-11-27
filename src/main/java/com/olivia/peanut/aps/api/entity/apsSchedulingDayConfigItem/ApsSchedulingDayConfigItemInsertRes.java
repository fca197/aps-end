package com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigItem;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 排程版本配置表(ApsSchedulingDayConfigItem)保存返回
 *
 * @author peanut
 * @since 2024-07-19 19:19:51
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingDayConfigItemInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

