package com.olivia.peanut.portal.api.entity.room;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class RoomSaveBatchRes {

  private List<Long> idList;
}
