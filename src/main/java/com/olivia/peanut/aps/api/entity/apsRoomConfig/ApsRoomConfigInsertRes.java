package com.olivia.peanut.aps.api.entity.apsRoomConfig;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsRoomConfig)保存返回
 *
 * @author peanut
 * @since 2024-04-01 15:27:30
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsRoomConfigInsertRes {

  /****
   * 写入行数
   */
  private int count;

}

