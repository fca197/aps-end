package com.olivia.peanut.portal.api.entity.room;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 房间信息(Room)保存返回
 *
 * @author makejava
 * @since 2024-03-11 17:20:54
 */
@Accessors(chain = true)
@Getter
@Setter

public class RoomInsertRes {

  /****
   * 写入行数
   */
  private int count;

}

