package com.olivia.peanut.aps.api.entity.apsGoodsForecastMakeBomUse;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecastMakeBomUse)查询对象入参
 *
 * @author peanut
 * @since 2024-05-15 10:26:03
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastMakeBomUseExportQueryPageListReq {

  private int pageNum;
  private int pageSize;
  private Boolean queryPage = true;
  private ApsGoodsForecastMakeBomUseDto data;


}

