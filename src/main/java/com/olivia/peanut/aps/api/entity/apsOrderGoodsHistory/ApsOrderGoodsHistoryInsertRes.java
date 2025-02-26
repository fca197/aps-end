package com.olivia.peanut.aps.api.entity.apsOrderGoodsHistory;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 历史订单记录(ApsOrderGoodsHistory)保存返回
 *
 * @author makejava
 * @since 2025-02-20 17:18:46
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderGoodsHistoryInsertRes {
  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

