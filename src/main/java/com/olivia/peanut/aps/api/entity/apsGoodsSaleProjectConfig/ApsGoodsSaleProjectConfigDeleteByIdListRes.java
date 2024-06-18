package com.olivia.peanut.aps.api.entity.apsGoodsSaleProjectConfig;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsSaleProjectConfig)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-04-27 16:07:22
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsSaleProjectConfigDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

