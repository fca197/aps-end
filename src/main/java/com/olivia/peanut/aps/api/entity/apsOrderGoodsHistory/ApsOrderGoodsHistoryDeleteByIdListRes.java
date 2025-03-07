package com.olivia.peanut.aps.api.entity.apsOrderGoodsHistory;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 历史订单记录(ApsOrderGoodsHistory)根据ID删除多个反参
 *
 * @author makejava
 * @since 2025-02-20 17:18:46
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderGoodsHistoryDeleteByIdListRes {
  /***
   * 受影响行数
   */
  private int count;

}

