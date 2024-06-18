package com.olivia.peanut.aps.api.entity.apsGoodsForecastMainMake;

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
public class ApsGoodsForecastMainMakeQueryDataByIdReq {

  @NotNull(message = "id不能为空")
  private Long id;
  private String beginDate;
  private String endDate;
}
