package com.olivia.peanut.aps.api.entity.apsRoomConfig;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsRoomConfig)表实体类
 *
 * @author peanut
 * @since 2024-04-01 15:27:30
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsRoomConfigUpdateByIdReq extends ApsRoomConfigDto {


  public void checkParam() {
  }

}

