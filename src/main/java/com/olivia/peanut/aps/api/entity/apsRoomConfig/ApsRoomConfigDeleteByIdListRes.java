package com.olivia.peanut.aps.api.entity.apsRoomConfig;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsRoomConfig)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-04-01 15:27:30
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsRoomConfigDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

