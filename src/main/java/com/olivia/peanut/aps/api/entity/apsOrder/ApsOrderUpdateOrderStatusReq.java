package com.olivia.peanut.aps.api.entity.apsOrder;

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
public class ApsOrderUpdateOrderStatusReq {

  @NotNull(message = "orderId不能为空")
  private Long orderId;
  @NotNull(message = "goodsStatusId不能为空")
  private Long goodsStatusId;

  private Boolean isBeginTime = Boolean.TRUE;
}
