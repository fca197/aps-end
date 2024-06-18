package com.olivia.peanut.aps.api.entity.apsMakeCapacityGoods;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsMakeCapacityGoods)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-04-13 12:05:05
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsMakeCapacityGoodsDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

