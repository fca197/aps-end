package com.olivia.peanut.aps.api.entity.apsOrderGoodsHistory;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 历史订单记录(ApsOrderGoodsHistory)查询对象入参
 *
 * @author makejava
 * @since 2025-02-20 17:18:46
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderGoodsHistoryQueryByIdListReq {
  private List<Long> idList;

}

