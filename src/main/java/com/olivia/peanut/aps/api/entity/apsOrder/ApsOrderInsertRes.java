package com.olivia.peanut.aps.api.entity.apsOrder;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsOrder)保存返回
 *
 * @author peanut
 * @since 2024-04-09 13:19:35
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

