package com.olivia.peanut.aps.api.entity.apsGoodsForecastComputeSaleData;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * (ApsGoodsForecastComputeSaleData)查询对象返回
 *
 * @author peanut
 * @since 2024-03-31 20:58:35
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastComputeSaleDataQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<ApsGoodsForecastComputeSaleDataDto> dataList;


}

