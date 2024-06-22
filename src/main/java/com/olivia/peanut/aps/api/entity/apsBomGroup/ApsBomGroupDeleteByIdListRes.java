package com.olivia.peanut.aps.api.entity.apsBomGroup;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 零件组配置(ApsBomGroup)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-06-19 17:41:23
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsBomGroupDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

