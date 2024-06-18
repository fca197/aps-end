package com.olivia.peanut.aps.api.entity.apsRoom;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsRoom)保存返回
 *
 * @author peanut
 * @since 2024-04-01 15:27:29
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsRoomInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

