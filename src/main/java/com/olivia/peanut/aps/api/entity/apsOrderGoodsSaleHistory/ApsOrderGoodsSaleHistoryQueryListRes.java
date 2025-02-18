package com.olivia.peanut.aps.api.entity.apsOrderGoodsSaleHistory;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 销售规划订单历史销售占比(ApsOrderGoodsSaleHistory)查询对象返回
 *
 * @author makejava
 * @since 2025-02-18 14:28:41
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderGoodsSaleHistoryQueryListRes {
  /***
   * 返回对象列表
   */
  private List<ApsOrderGoodsSaleHistoryDto> dataList;


}

