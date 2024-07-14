package com.olivia.peanut.aps.api.entity.apsOrderRollingForecast;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 滚动预测(ApsOrderRollingForecast)保存入参
 *
 * @author peanut
 * @since 2024-07-14 19:43:51
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderRollingForecastInsertReq extends ApsOrderRollingForecastDto {

  public void checkParam() {
  }
}

