package com.olivia.peanut.aps.api.entity.apsGoodsForecastComputeSaleData;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecastComputeSaleData)保存返回
 *
 * @author peanut
 * @since 2024-03-31 20:58:34
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastComputeSaleDataInsertRes {

  /****
   * 写入行数
   */
  private int count;

}

