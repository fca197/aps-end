package com.olivia.peanut.aps.api.entity.apsGoodsForecastUserSaleData;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecastUserSaleData)保存返回
 *
 * @author peanut
 * @since 2024-03-30 18:29:07
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastUserSaleDataInsertRes {

  /****
   * 写入行数
   */
  private int count;

}

