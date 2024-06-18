package com.olivia.peanut.aps.api.entity.apsGoodsForecastMainSaleData;

import java.util.List;
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
public class ApsGoodsForecastMainSaleDataQueryByIdListReq {

  private List<Long> idList;


  public void checkParam() {
  }

}

