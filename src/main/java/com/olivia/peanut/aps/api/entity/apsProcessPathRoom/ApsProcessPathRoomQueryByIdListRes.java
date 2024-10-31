package com.olivia.peanut.aps.api.entity.apsProcessPathRoom;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsProcessPathRoom)查询对象返回
 *
 * @author peanut
 * @since 2024-04-01 17:49:19
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsProcessPathRoomQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<ApsProcessPathRoomDto> dataList;


}

