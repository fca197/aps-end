package com.olivia.peanut.aps.api.entity.apsRollingForecastOrderItem;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 滚动预测订单节点表(ApsRollingForecastOrderItem)保存返回
 *
 * @author peanut
 * @since 2024-07-16 10:31:19
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsRollingForecastOrderItemInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

