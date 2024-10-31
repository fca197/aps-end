package com.olivia.peanut.aps.api.entity.apsGoodsForecastComputeSaleData;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecastComputeSaleData)查询对象入参
 *
 * @author peanut
 * @since 2024-03-31 20:58:35
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastComputeSaleDataQueryByIdListReq {

  private List<Long> idList;


  public void checkParam() {
  }

}

