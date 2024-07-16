package com.olivia.peanut.aps.api.entity.apsRollingForecastOrderItem;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 滚动预测订单节点表(ApsRollingForecastOrderItem)表实体类
 *
 * @author peanut
 * @since 2024-07-16 10:31:19
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsRollingForecastOrderItemUpdateByIdReq extends ApsRollingForecastOrderItemDto {


  public void checkParam() {
  }

}
