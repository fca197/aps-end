package com.olivia.peanut.aps.api.entity.apsOrderGoodsSaleHistory;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 销售规划订单历史销售占比(ApsOrderGoodsSaleHistory)查询对象入参
 *
 * @author makejava
 * @since 2025-02-18 14:28:42
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderGoodsSaleHistoryQueryByIdListReq {
  private List<Long> idList;

}

