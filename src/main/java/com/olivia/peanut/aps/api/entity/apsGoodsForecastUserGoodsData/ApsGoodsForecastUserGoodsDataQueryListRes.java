package com.olivia.peanut.aps.api.entity.apsGoodsForecastUserGoodsData;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecastUserGoodsData)查询对象返回
 *
 * @author peanut
 * @since 2024-03-30 18:29:06
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastUserGoodsDataQueryListRes {

  /***
   * 返回对象列表
   */
  private List<ApsGoodsForecastUserGoodsDataDto> dataList;


}

