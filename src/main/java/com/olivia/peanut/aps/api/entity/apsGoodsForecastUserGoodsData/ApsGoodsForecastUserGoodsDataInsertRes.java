package com.olivia.peanut.aps.api.entity.apsGoodsForecastUserGoodsData;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecastUserGoodsData)保存返回
 *
 * @author peanut
 * @since 2024-03-30 18:29:06
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastUserGoodsDataInsertRes {

  /****
   * 写入行数
   */
  private int count;

}

