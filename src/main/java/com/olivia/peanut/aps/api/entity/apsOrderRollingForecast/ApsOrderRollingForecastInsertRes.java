package com.olivia.peanut.aps.api.entity.apsOrderRollingForecast;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 滚动预测(ApsOrderRollingForecast)保存返回
 *
 * @author peanut
 * @since 2024-07-14 19:43:51
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderRollingForecastInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

