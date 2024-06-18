package com.olivia.peanut.aps.api.entity.apsGoodsForecastMake;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecastMake)保存返回
 *
 * @author peanut
 * @since 2024-04-07 15:07:48
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastMakeInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

