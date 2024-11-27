package com.olivia.peanut.aps.api.entity.apsGoodsForecastMainSaleData;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * (ApsGoodsForecastMainSaleData)查询对象返回
 *
 * @author peanut
 * @since 2024-04-02 09:42:28
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastMainSaleDataQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<ApsGoodsForecastMainSaleDataDto> dataList;


}

