package com.olivia.peanut.aps.api.entity.apsGoodsForecastMakeSaleData;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecastMakeSaleData)保存入参
 *
 * @author peanut
 * @since 2024-04-07 15:07:49
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastMakeSaleDataInsertReq extends ApsGoodsForecastMakeSaleDataDto {

  public void checkParam() {
  }
}

