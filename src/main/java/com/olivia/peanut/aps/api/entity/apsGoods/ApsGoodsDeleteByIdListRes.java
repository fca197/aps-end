package com.olivia.peanut.aps.api.entity.apsGoods;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoods)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-03-29 16:11:23
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

