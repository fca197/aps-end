package com.olivia.peanut.aps.api.entity.apsGoodsForecastMainGoodsData;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecastMainGoodsData)保存返回
 *
 * @author peanut
 * @since 2024-04-02 13:44:28
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastMainGoodsDataInsertRes {

  /****
   * 写入行数
   */
  private int count;

}

