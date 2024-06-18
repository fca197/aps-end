package com.olivia.peanut.aps.api.entity.apsOrderGoodsForecastMake;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 订单商品节点预测表(ApsOrderGoodsForecastMake)保存返回
 *
 * @author peanut
 * @since 2024-06-02 23:11:40
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderGoodsForecastMakeInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

