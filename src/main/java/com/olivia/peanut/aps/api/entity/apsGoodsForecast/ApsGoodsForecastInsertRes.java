package com.olivia.peanut.aps.api.entity.apsGoodsForecast;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecast)保存返回
 *
 * @author peanut
 * @since 2024-03-30 13:38:52
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastInsertRes {

  /****
   * 写入行数
   */
  private int count;

}

