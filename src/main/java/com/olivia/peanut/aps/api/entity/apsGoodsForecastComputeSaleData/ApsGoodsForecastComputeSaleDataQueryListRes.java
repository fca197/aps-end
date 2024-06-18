package com.olivia.peanut.aps.api.entity.apsGoodsForecastComputeSaleData;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecastComputeSaleData)查询对象返回
 *
 * @author peanut
 * @since 2024-03-31 20:58:34
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastComputeSaleDataQueryListRes {

  /***
   * 返回对象列表
   */
  private List<ApsGoodsForecastComputeSaleDataDto> dataList;


}

