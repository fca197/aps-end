package com.olivia.peanut.aps.api.entity.apsOrderGoodsStatusDate;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 订单商品状态表(ApsOrderGoodsStatusDate)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-06-14 10:26:58
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderGoodsStatusDateDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

