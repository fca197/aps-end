package com.olivia.peanut.aps.api.entity.apsRollingForecastOrderItem;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 滚动预测订单节点表(ApsRollingForecastOrderItem)查询对象入参
 *
 * @author peanut
 * @since 2024-07-16 10:31:19
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsRollingForecastOrderItemExportQueryPageListReq {

  private int pageNum;
  private int pageSize;
  private Boolean queryPage = true;
  private ApsRollingForecastOrderItemDto data;


  public void checkParam() {
  }

}
