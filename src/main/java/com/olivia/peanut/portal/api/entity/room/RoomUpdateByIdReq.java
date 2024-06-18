package com.olivia.peanut.portal.api.entity.room;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 房间信息(Room)表实体类
 *
 * @author makejava
 * @since 2024-03-11 17:20:54
 */
@Accessors(chain = true)
@Getter
@Setter

public class RoomUpdateByIdReq extends RoomDto {


  public void checkParam() {
  }

}

