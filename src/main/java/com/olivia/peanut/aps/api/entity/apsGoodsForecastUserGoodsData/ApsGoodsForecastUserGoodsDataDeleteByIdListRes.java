package com.olivia.peanut.aps.api.entity.apsGoodsForecastUserGoodsData;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecastUserGoodsData)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-03-30 18:29:06
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastUserGoodsDataDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

