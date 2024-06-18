package com.olivia.peanut.aps.api.entity.apsGoodsForecastMainMakeSaleData;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecastMainMakeSaleData)保存返回
 *
 * @author peanut
 * @since 2024-04-08 09:52:50
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastMainMakeSaleDataInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

