package com.olivia.peanut.aps.api.entity.apsSchedulingVersionItem;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsSchedulingVersionItem)保存返回
 *
 * @author peanut
 * @since 2024-04-16 09:24:05
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingVersionItemInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

