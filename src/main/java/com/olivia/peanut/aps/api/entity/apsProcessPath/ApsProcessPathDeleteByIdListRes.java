package com.olivia.peanut.aps.api.entity.apsProcessPath;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsProcessPath)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-04-01 17:49:18
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsProcessPathDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

