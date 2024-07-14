package com.olivia.peanut.aps.api.entity.apsRollingForecastOrder;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 滚动预测(ApsRollingForecastOrder)查询对象入参
 *
 * @author peanut
 * @since 2024-07-14 20:22:28
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsRollingForecastOrderQueryListReq {

  private ApsRollingForecastOrderDto data;
}

