package com.olivia.peanut.aps.api.entity.apsSchedulingVersion;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsSchedulingVersion)保存返回
 *
 * @author peanut
 * @since 2024-04-13 21:32:14
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingVersionInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

