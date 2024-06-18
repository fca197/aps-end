package com.olivia.peanut.aps.api.entity.apsStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsStatus)保存返回
 *
 * @author peanut
 * @since 2024-04-01 10:50:12
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsStatusInsertRes {

  /****
   * 写入行数
   */
  private int count;

}

