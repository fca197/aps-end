package com.olivia.peanut.portal.api.entity.room;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 房间信息(Room)查询对象入参
 *
 * @author makejava
 * @since 2024-03-11 17:20:55
 */
@Accessors(chain = true)
@Getter
@Setter

public class RoomQueryByIdListReq {

  private List<Long> idList;


}

