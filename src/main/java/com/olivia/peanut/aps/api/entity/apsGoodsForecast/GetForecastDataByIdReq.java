package com.olivia.peanut.aps.api.entity.apsGoodsForecast;

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
public class GetForecastDataByIdReq {

  @NotNull(message = "id不能为空")
  private Long id;
}
