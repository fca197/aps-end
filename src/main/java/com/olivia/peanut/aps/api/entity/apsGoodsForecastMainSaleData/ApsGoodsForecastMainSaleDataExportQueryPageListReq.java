package com.olivia.peanut.aps.api.entity.apsGoodsForecastMainSaleData;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecastMainSaleData)查询对象入参
 *
 * @author peanut
 * @since 2024-04-02 09:42:28
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastMainSaleDataExportQueryPageListReq {

  private int pageNum;
  private int pageSize;
  private Boolean queryPage = true;
  private ApsGoodsForecastMainSaleDataDto data;


  public void checkParam() {
  }

}

