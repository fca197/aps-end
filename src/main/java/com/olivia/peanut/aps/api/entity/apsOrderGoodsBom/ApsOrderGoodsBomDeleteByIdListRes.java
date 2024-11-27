package com.olivia.peanut.aps.api.entity.apsOrderGoodsBom;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 订单商品零件表(ApsOrderGoodsBom)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-07-30 10:28:23
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderGoodsBomDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

