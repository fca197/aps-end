package com.olivia.peanut.aps.api.entity.apsSchedulingConstraints;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsSchedulingConstraints)保存返回
 *
 * @author peanut
 * @since 2024-04-13 21:32:13
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingConstraintsInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

