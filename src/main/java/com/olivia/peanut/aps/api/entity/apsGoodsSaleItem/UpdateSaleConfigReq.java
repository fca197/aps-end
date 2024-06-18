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
public class UpdateSaleConfigReq {

  @NotNull(message = "goodsId不能为空")
  private Long goodsId;
  @NotNull(message = "saleConfigId不能为空")
  private Long saleConfigId;
  @NotNull(message = "isAdd不能为空")
  private Boolean isAdd;
}
