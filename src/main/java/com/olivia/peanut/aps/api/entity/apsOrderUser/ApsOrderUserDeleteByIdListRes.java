package com.olivia.peanut.aps.api.entity.apsOrderUser;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsOrderUser)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-04-09 13:19:39
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderUserDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

