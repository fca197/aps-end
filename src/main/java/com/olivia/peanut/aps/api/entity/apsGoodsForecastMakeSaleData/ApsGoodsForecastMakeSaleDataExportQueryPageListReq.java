package com.olivia.peanut.aps.api.entity.apsGoodsForecastMakeSaleData;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecastMakeSaleData)查询对象入参
 *
 * @author peanut
 * @since 2024-04-07 15:07:49
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastMakeSaleDataExportQueryPageListReq {

  private int pageNum;
  private int pageSize;
  private Boolean queryPage = true;
  private ApsGoodsForecastMakeSaleDataDto data;


}

