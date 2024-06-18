package com.olivia.peanut.aps.api.entity.apsSaleConfig;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsSaleConfig)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-03-29 23:10:39
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSaleConfigDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

