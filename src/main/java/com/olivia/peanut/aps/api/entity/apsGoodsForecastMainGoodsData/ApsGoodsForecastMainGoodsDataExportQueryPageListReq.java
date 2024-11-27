package com.olivia.peanut.aps.api.entity.apsGoodsForecastMainGoodsData;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecastMainGoodsData)查询对象入参
 *
 * @author peanut
 * @since 2024-04-02 13:44:28
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastMainGoodsDataExportQueryPageListReq {

  private int pageNum;
  private int pageSize;
  private Boolean queryPage = true;
  private ApsGoodsForecastMainGoodsDataDto data;


}

