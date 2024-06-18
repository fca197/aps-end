package com.olivia.peanut.aps.api.entity.apsGoodsBomBuyPlanItem;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * BOM 购买清单(ApsGoodsBomBuyPlanItem)保存返回
 *
 * @author peanut
 * @since 2024-06-05 14:35:30
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsBomBuyPlanItemInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

