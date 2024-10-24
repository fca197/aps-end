package com.olivia.peanut.aps.api.entity.apsGoodsForecastMainMakeSaleData;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecastMainMakeSaleData)查询对象入参
 *
 * @author peanut
 * @since 2024-04-08 09:52:54
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastMainMakeSaleDataQueryByIdListReq {

  private List<Long> idList;


  public void checkParam() {
  }

}

