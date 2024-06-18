package com.olivia.peanut.aps.api.entity.apsGoodsForecastMainMake;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecastMainMake)保存返回
 *
 * @author peanut
 * @since 2024-04-08 09:52:49
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastMainMakeInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

