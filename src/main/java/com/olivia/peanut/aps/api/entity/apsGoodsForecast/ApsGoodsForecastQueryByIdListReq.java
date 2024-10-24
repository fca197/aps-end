package com.olivia.peanut.aps.api.entity.apsGoodsForecast;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecast)查询对象入参
 *
 * @author peanut
 * @since 2024-03-30 13:38:53
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastQueryByIdListReq {

  private List<Long> idList;


  public void checkParam() {
  }

}

