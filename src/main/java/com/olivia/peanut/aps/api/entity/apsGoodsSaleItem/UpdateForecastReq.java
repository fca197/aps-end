package com.olivia.peanut.aps.api.entity.apsGoodsSaleItem;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class UpdateForecastReq {

  @NotNull(message = "goodsId不能为空")
  private Long goodsId;
  @NotNull(message = "saleConfigId不能为空")
  private Long saleConfigId;
  @NotNull(message = "isForecast不能为空")
  private Integer useForecast;
}
