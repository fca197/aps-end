package com.olivia.peanut.aps.api.entity.apsRoomConfig;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsRoomConfig)查询对象返回
 *
 * @author peanut
 * @since 2024-04-01 15:27:30
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsRoomConfigQueryListRes {

  /***
   * 返回对象列表
   */
  private List<ApsRoomConfigDto> dataList;


}

