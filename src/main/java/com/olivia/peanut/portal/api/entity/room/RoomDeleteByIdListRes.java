package com.olivia.peanut.portal.api.entity.room;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 房间信息(Room)根据ID删除多个反参
 *
 * @author makejava
 * @since 2024-03-11 17:20:54
 */
@Accessors(chain = true)
@Getter
@Setter

public class RoomDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

