package com.olivia.peanut.aps.api.entity.apsSchedulingGoodsBomTotal;

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
public class ApsSchedulingGoodsBomTotalCreateBomBuyPlanReq {

  @NotNull
  private Long schedulingVersionId;
}
