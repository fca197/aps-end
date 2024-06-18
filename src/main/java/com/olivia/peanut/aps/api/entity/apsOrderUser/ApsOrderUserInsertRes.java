package com.olivia.peanut.aps.api.entity.apsOrderUser;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsOrderUser)保存返回
 *
 * @author peanut
 * @since 2024-04-09 13:19:39
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderUserInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

