package com.olivia.peanut.aps.api.entity.apsGoodsSaleItem;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsSaleItem)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-03-30 10:38:36
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsSaleItemDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

