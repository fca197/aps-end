package com.olivia.peanut.aps.api.entity.apsRoom;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsRoom)保存入参
 *
 * @author peanut
 * @since 2024-04-01 15:27:29
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsRoomInsertReq extends ApsRoomDto {

  public void checkParam() {

  }
}

