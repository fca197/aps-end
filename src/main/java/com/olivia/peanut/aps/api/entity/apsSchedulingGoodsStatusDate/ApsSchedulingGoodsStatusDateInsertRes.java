package com.olivia.peanut.aps.api.entity.apsSchedulingGoodsStatusDate;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 订单商品状态表(ApsSchedulingGoodsStatusDate)保存返回
 *
 * @author peanut
 * @since 2024-06-14 21:35:09
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingGoodsStatusDateInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

