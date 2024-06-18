package com.olivia.peanut.aps.api.entity.apsGoodsForecast;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecast)表实体类
 *
 * @author peanut
 * @since 2024-03-30 13:38:53
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastUpdateByIdReq extends ApsGoodsForecastDto {


  public void checkParam() {
    this.buildMonthList();
  }

}

