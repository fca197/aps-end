package com.olivia.peanut.aps.api.entity.apsProcessPathRoom;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsProcessPathRoom)保存返回
 *
 * @author peanut
 * @since 2024-04-01 17:49:19
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsProcessPathRoomInsertRes {

  /****
   * 写入行数
   */
  private int count;

}

