package com.olivia.peanut.aps.api.entity.apsSchedulingGoodsBom;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 订单商品零件表(ApsSchedulingGoodsBom)保存返回
 *
 * @author peanut
 * @since 2024-06-02 21:50:24
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingGoodsBomInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

