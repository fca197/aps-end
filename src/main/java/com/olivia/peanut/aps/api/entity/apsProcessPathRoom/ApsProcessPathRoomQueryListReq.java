package com.olivia.peanut.aps.api.entity.apsProcessPathRoom;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsProcessPathRoom)查询对象入参
 *
 * @author peanut
 * @since 2024-04-01 17:49:19
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsProcessPathRoomQueryListReq {

  private ApsProcessPathRoomDto data;
}

