package com.olivia.peanut.aps.api.entity.apsGoodsForecastMakeSaleData;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecastMakeSaleData)查询对象返回
 *
 * @author peanut
 * @since 2024-04-07 15:07:55
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastMakeSaleDataQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<ApsGoodsForecastMakeSaleDataDto> dataList;


}

